package faewulf.squaremap.banner.dataType;

import faewulf.squaremap.banner.Squaremapbanner;
import net.minecraft.world.item.DyeColor;
import xyz.jpenilla.squaremap.api.Key;
import xyz.jpenilla.squaremap.api.SquaremapProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class icon {
	public static final Key BLACK = register("BLACK");
	public static final Key BLUE = register("BLUE");
	public static final Key BROWN = register("BROWN");
	public static final Key CYAN = register("CYAN");
	public static final Key GRAY = register("GRAY");
	public static final Key GREEN = register("GREEN");
	public static final Key LIGHT_BLUE = register("LIGHT_BLUE");
	public static final Key LIGHT_GRAY = register("LIGHT_GRAY");
	public static final Key LIME = register("LIME");
	public static final Key MAGENTA = register("MAGENTA");
	public static final Key ORANGE = register("ORANGE");
	public static final Key PINK = register("PINK");
	public static final Key PURPLE = register("PURPLE");
	public static final Key RED = register("RED");
	public static final Key WHITE = register("WHITE");
	public static final Key YELLOW = register("YELLOW");

	private static Key register(String name) {
		String filename = "assets/squaremap-banner/textures/icons/" + name + ".png";
		Key key = Key.of(name);

		BufferedImage image = getBufferImage(filename);
		SquaremapProvider.get().iconRegistry().register(key, image);
		return key;
	}

	public static Key getIcon(DyeColor type) {
		return switch (type) {
			case BLACK -> BLACK;
			case BLUE -> BLUE;
			case BROWN -> BROWN;
			case CYAN -> CYAN;
			case GRAY -> GRAY;
			case GREEN -> GREEN;
			case LIGHT_BLUE -> LIGHT_BLUE;
			case LIGHT_GRAY -> LIGHT_GRAY;
			case LIME -> LIME;
			case MAGENTA -> MAGENTA;
			case ORANGE -> ORANGE;
			case PINK -> PINK;
			case PURPLE -> PURPLE;
			case RED -> RED;
			case YELLOW -> YELLOW;
			default -> WHITE;
		};
	}

	private static BufferedImage getBufferImage(String resourcePath) {
		try (InputStream inputStream = icon.class.getClassLoader().getResourceAsStream(resourcePath)) {
			if (inputStream == null) {
				throw new RuntimeException("Resource not found: " + resourcePath);
			}
			return ImageIO.read(inputStream);
		} catch (IOException e) {
			Squaremapbanner.LOGGER.warn("Failed to register banners icon", e);
			return null;
		}
	}

}
