/*
 * 文件名：Test.java 版权：Copyright by www.sdhuijin.cn 描述： 修改人：Administrator 修改时间：2018年9月26日 修改内容：
 */

package com.lizc.sports.common.utils;


import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class PhotoUtils
{
    protected Logger logger = LoggerFactory.getLogger(getClass());

    // public static void main(String[] args)
    // throws IOException
    // {
    // byte[] bytes = FileUtils.readFileToByteArray(
    // new File("C:\\Users\\Administrator\\Desktop\\微信图片.jpg"));
    // bytes = PhotoUtils.compressPicForScale(bytes, 250);
    // FileUtils.writeByteArrayToFile(new File("C:\\Users\\Administrator\\Desktop\\微信图片.jpg"),
    // bytes);
    // }

    /**
     * * 根据指定大小压缩图片 * * @param imageBytes 源图片字节数组 * @param desFileSize 指定图片大小，单位kb * @param imageId
     * 影像编号 * @return 压缩质量后的图片字节数组
     */
    public static byte[] compressPicForScale(byte[] imageBytes, long desFileSize)
    {
        if (imageBytes == null || imageBytes.length <= 0 || imageBytes.length < desFileSize * 1024)
        {
            return imageBytes;
        }
        long srcSize = imageBytes.length;
        double accuracy = getAccuracy(srcSize / 1024);
        try
        {
            while (imageBytes.length > desFileSize * 1024)
            {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
                Thumbnails.of(inputStream).scale(accuracy).outputQuality(accuracy).toOutputStream(
                    outputStream);
                imageBytes = outputStream.toByteArray();
            }
        }
        catch (Exception e)
        {}
        return imageBytes;
    }

    /** * 自动调节精度(经验数值) * * @param size 源图片大小 * @return 图片压缩质量比 */
    private static double getAccuracy(long size)
    {
        double accuracy;
        if (size < 900)
        {
            accuracy = 0.85;
        }
        else if (size < 2047)
        {
            accuracy = 0.6;
        }
        else if (size < 3275)
        {
            accuracy = 0.44;
        }
        else
        {
            accuracy = 0.4;
        }
        return accuracy;
    }

}
