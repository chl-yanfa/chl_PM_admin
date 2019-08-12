package com.car.auction.common;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.google.code.kaptcha.GimpyEngine;
import com.google.code.kaptcha.util.Configurable;

/**
 * Company:SDIC_Inner
 * Description：覆盖原google动态验证码效果
 * @author 刘民
 * @date 2014-7-29
 * @use 主要用于处理登陆页面验证码样式
 **/
public class ShadowGimpy extends Configurable implements GimpyEngine
{
	public BufferedImage getDistortedImage(BufferedImage baseImage)
	{
		BufferedImage distortedImage = new BufferedImage(baseImage.getWidth(),
				baseImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

		Graphics2D graph = (Graphics2D) distortedImage.getGraphics();
		graph.drawImage(baseImage, 0, 0, null, null);
		graph.dispose();

		return distortedImage;
	}
}
