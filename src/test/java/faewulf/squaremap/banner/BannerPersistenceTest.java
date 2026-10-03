package faewulf.squaremap.banner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import faewulf.squaremap.banner.dataType.Data;
import faewulf.squaremap.banner.dataType.DataTypeAdapter;
import faewulf.squaremap.banner.dataType.ModConfigs;
import faewulf.squaremap.banner.utils.BannerMarker;
import org.junit.jupiter.api.Test;
import xyz.jpenilla.squaremap.api.Key;
import xyz.jpenilla.squaremap.api.Point;

import static org.junit.jupiter.api.Assertions.*;

class BannerPersistenceTest {
	private final Gson gson = new GsonBuilder().registerTypeAdapter(Data.class, new DataTypeAdapter()).create();

	@Test
	void preservesNameColorLocationAndTooltipAfterReload() {
		String name = "Estación <Norte> & \"Casa\" 'Sur'";
		Key icon = Key.of("RED");
		Key id = Key.of("saved-banner-id");
		Data original = new Data(BannerMarker.create(Point.point(-123, 456), icon, name), icon, name, id);

		Data restored = gson.fromJson(gson.toJson(original), Data.class);

		assertEquals(name, restored.name());
		assertEquals(id, restored.key());
		assertEquals(icon, restored.keyIconType());
		assertEquals(original.marker(), restored.marker());
		assertEquals("Estación &lt;Norte&gt; &amp; &quot;Casa&quot; &#39;Sur&#39;", restored.marker().markerOptions().hoverTooltip());
	}

	@Test
	void restoresLegacySaveWithUnknownFields() {
		Data restored = gson.fromJson("""
			{"key":"legacy-id","name":"Casa","type":"BLUE","x":4,"z":8,"future":{"value":1}}
			""", Data.class);

		assertEquals("Casa", restored.marker().markerOptions().hoverTooltip());
		assertEquals(4, restored.marker().point().x());
		assertEquals(8, restored.marker().point().z());
		assertEquals(Key.of("BLUE"), restored.marker().image());
	}

	@Test
	void dropsRemovedAreaSettingsAndKeepsBlacklist() {
		ModConfigs config = gson.fromJson("""
			{"banner_radius":80,"announce_when_near_banner":true,"blacklist":["example"]}
			""", ModConfigs.class);

		assertEquals("{\"blacklist\":[\"example\"]}", gson.toJson(config));
	}
}
