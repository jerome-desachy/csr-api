package com.naturalmotion.csr_api.service.gift;

import com.naturalmotion.csr_api.api.FusionColor;
import com.naturalmotion.csr_api.service.car.CarException;
import com.naturalmotion.csr_api.service.io.JsonCopy;
import com.naturalmotion.csr_api.service.io.NsbException;
import com.naturalmotion.csr_api.service.io.NsbReader;
import com.naturalmotion.csr_api.service.io.NsbWriter;

import javax.json.*;
import java.io.File;
import java.util.List;

public class GiftServiceFileImpl implements GiftService {

    private NsbWriter nsbWriter = new NsbWriter();

    private NsbReader nsbReader = new NsbReader();

    private JsonCopy jsonCopy = new JsonCopy();

    private GiftBuilder builder = new GiftBuilder();

    private final String path;

    public GiftServiceFileImpl(String path) {
        this.path = path;
    }

    @Override
    public JsonObject addEssence() throws NsbException {
        JsonObjectBuilder gift = builder.buildEssence("0_0");

        File nsb = nsbReader.getNsbFile(path);
        JsonObject nsbObject = nsbReader.readJsonObject(nsb);

        JsonObjectBuilder newNsb = addGift(gift, nsbObject);
        nsbWriter.writeNsb(nsb, newNsb);
        return newNsb.build();
    }

    @Override
    public JsonObject addFusions(List<FusionColor> colors, List<String> brands) {
        return null;
    }

    @Override
    public JsonObject addEliteToken() {
        return null;
    }

    @Override
    public JsonObject addRestorationToken(String carId) {
        return null;
    }

    private JsonObjectBuilder addGift(JsonObjectBuilder gift, JsonObject nsbObject) {
        JsonObjectBuilder newNsb = jsonCopy.copyObject(nsbObject);

        JsonArray picl = nsbObject.getJsonArray("picl");
        JsonArrayBuilder piclBuilder = Json.createArrayBuilder(picl);
        piclBuilder.add("eRewards");
        newNsb.add("picl", piclBuilder);

        JsonArray playinbitms = nsbObject.getJsonArray("playinbitms");
        JsonArrayBuilder itmsBuilder = Json.createArrayBuilder(playinbitms);
        itmsBuilder.add(gift);
        newNsb.add("playinbitms", itmsBuilder);
        return newNsb;
    }
}