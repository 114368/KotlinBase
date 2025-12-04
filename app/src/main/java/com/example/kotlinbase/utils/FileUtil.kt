package com.example.kotlinbase.utils

import android.content.Context
import android.text.format.Formatter
import android.util.Log
import android.webkit.MimeTypeMap
import java.io.*
import java.nio.charset.Charset

/**
 * 文件工具类（Kotlin）
 *
 * 注意：
 * - 只对 app 可直接访问的路径生效（内部存储 / app 私有目录 / 已授予权限的外部路径）。
 * - 对公共外部存储（下载/图片/视频等）在 Android 10+ 仍建议走 MediaStore / SAF。
 */
object FileUtil {

    private const val TAG = "FileUtil"

    /**
     * 通过路径获取 File（不会做存在性检查）
     */
    @JvmStatic
    fun getFile(path: String?): File? {
        if (path.isNullOrBlank()) return null
        return File(path)
    }

    /**
     * 文件或目录是否存在
     */
    @JvmStatic
    fun exists(file: File?): Boolean {
        return file != null && file.exists()
    }

    /**
     * 创建目录（包含多级），已存在则返回 true
     */
    @JvmStatic
    fun createDir(dir: File?): Boolean {
        if (dir == null) return false
        if (dir.exists()) return dir.isDirectory
        return try {
            dir.mkdirs()
        } catch (e: Exception) {
            Log.e(TAG, "createDir failed: ${dir.absolutePath}", e)
            false
        }
    }

    /**
     * 创建空文件，若父目录不存在则尝试创建
     * 已存在则返回 true
     */
    @JvmStatic
    fun createFile(file: File?): Boolean {
        if (file == null) return false
        if (file.exists()) return file.isFile
        return try {
            val parent = file.parentFile
            if (parent != null && !parent.exists()) {
                if (!parent.mkdirs()) {
                    Log.e(TAG, "createFile: mkdirs failed: ${parent.absolutePath}")
                    return false
                }
            }
            file.createNewFile()
        } catch (e: Exception) {
            Log.e(TAG, "createFile failed: ${file.absolutePath}", e)
            false
        }
    }

    /**
     * 删除文件或目录（目录则递归删除）
     */
    @JvmStatic
    fun delete(file: File?): Boolean {
        if (file == null || !file.exists()) return false
        return try {
            if (file.isDirectory) {
                file.listFiles()?.forEach { child ->
                    delete(child)
                }
            }
            file.delete()
        } catch (e: Exception) {
            Log.e(TAG, "delete failed: ${file.absolutePath}", e)
            false
        }
    }

    /**
     * 复制文件
     * @param overwrite 若目标已存在，是否覆盖
     */
    @JvmStatic
    fun copyFile(src: File?, dest: File?, overwrite: Boolean = false): Boolean {
        if (src == null || dest == null) return false
        if (!src.exists() || !src.isFile) {
            Log.e(TAG, "copyFile: src not exist or not file: ${src.absolutePath}")
            return false
        }
        if (dest.exists()) {
            if (!overwrite) {
                Log.w(TAG, "copyFile: dest exists and overwrite=false: ${dest.absolutePath}")
                return false
            }
            if (dest.isDirectory) {
                Log.e(TAG, "copyFile: dest is directory: ${dest.absolutePath}")
                return false
            }
        }
        return try {
            val parent = dest.parentFile
            if (parent != null && !parent.exists()) {
                if (!parent.mkdirs()) {
                    Log.e(TAG, "copyFile: mkdirs failed: ${parent.absolutePath}")
                    return false
                }
            }
            FileInputStream(src).use { input ->
                FileOutputStream(dest).use { output ->
                    val buffer = ByteArray(8 * 1024)
                    var len: Int
                    while (true) {
                        len = input.read(buffer)
                        if (len == -1) break
                        output.write(buffer, 0, len)
                    }
                    output.flush()
                }
            }
            true
        } catch (e: Exception) {
            Log.e(TAG, "copyFile failed: ${src.absolutePath} -> ${dest.absolutePath}", e)
            false
        }
    }

    /**
     * 移动文件：copy + delete
     */
    @JvmStatic
    fun moveFile(src: File?, dest: File?, overwrite: Boolean = false): Boolean {
        if (!copyFile(src, dest, overwrite)) return false
        return delete(src)
    }

    /**
     * 读取文件全部内容为 String
     */
    @JvmStatic
    fun readText(file: File?, charset: Charset = Charsets.UTF_8): String? {
        if (file == null || !file.exists() || !file.isFile) return null
        return try {
            file.inputStream().buffered().use { input ->
                input.readBytes().toString(charset)
            }
        } catch (e: Exception) {
            Log.e(TAG, "readText failed: ${file.absolutePath}", e)
            null
        }
    }

    /**
     * 写文本到文件
     * @param append 是否追加
     */
    @JvmStatic
    fun writeText(
        file: File?,
        text: String,
        append: Boolean = false,
        charset: Charset = Charsets.UTF_8
    ): Boolean {
        if (file == null) return false
        return try {
            val parent = file.parentFile
            if (parent != null && !parent.exists()) {
                if (!parent.mkdirs()) {
                    Log.e(TAG, "writeText: mkdirs failed: ${parent.absolutePath}")
                    return false
                }
            }
            FileOutputStream(file, append).use { fos ->
                fos.write(text.toByteArray(charset))
                fos.flush()
            }
            true
        } catch (e: Exception) {
            Log.e(TAG, "writeText failed: ${file.absolutePath}", e)
            false
        }
    }

    /**
     * 获取文件（或目录）的总大小，单位：字节
     */
    @JvmStatic
    fun getFileSize(file: File?): Long {
        if (file == null || !file.exists()) return 0L
        return try {
            if (file.isFile) return file.length()
            var size = 0L
            file.listFiles()?.forEach { child ->
                size += getFileSize(child)
            }
            size
        } catch (e: Exception) {
            Log.e(TAG, "getFileSize failed: ${file.absolutePath}", e)
            0L
        }
    }

    /**
     * 把字节大小格式化为可读字符串（KB、MB...）
     */
    @JvmStatic
    fun formatFileSize(context: Context, sizeBytes: Long): String {
        return Formatter.formatFileSize(context, sizeBytes)
    }

    /**
     * 获取文件扩展名（不包含点）
     */
    @JvmStatic
    fun getExtension(fileName: String?): String {
        if (fileName.isNullOrBlank()) return ""
        val lastDot = fileName.lastIndexOf('.')
        return if (lastDot >= 0 && lastDot < fileName.length - 1) {
            fileName.substring(lastDot + 1).lowercase()
        } else {
            ""
        }
    }

    /**
     * 根据扩展名推断 MIME 类型
     */
    @JvmStatic
    fun getMimeType(file: File?): String? {
        if (file == null || !file.exists()) return null
        val ext = getExtension(file.name)
        if (ext.isEmpty()) return null
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext)
    }
}
