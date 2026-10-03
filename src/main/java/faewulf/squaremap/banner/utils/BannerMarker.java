package faewulf.squaremap.banner.utils;

import xyz.jpenilla.squaremap.api.Key;
import xyz.jpenilla.squaremap.api.Point;
import xyz.jpenilla.squaremap.api.marker.Icon;
import xyz.jpenilla.squaremap.api.marker.Marker;
import xyz.jpenilla.squaremap.api.marker.MarkerOptions;

public final class BannerMarker {
	private BannerMarker() {
	}

	public static Icon create(Point point, Key iconKey, String name) {
		Icon marker = Marker.icon(point, iconKey, 16);
		marker.markerOptions(MarkerOptions.builder().hoverTooltip(escapeHtml(name)).build());
		return marker;
	}

	private static String escapeHtml(String text) {
		return text.replace("&", "&amp;")
			.replace("<", "&lt;")
			.replace(">", "&gt;")
			.replace("\"", "&quot;")
			.replace("'", "&#39;");
	}
}
