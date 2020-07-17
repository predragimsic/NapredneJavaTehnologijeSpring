/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njt.cineman.model.impl;

import java.io.IOException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.StdDeserializer;

/**
 *
 * @author cineman
 */
public class PropertyPresentDeserializer extends StdDeserializer<Content> {

    protected PropertyPresentDeserializer() {
        super(Content.class);
    }
    
    @Override
    public Content deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        JsonNode node = jp.readValueAsTree();

        // Select the concrete class based on the existence of a property
        if (node.get("budget")!= null) {
            return jp.getCodec().treeToValue(node, Movie.class);
        }
        return jp.getCodec().treeToValue(node, Show.class);
    }

    
    
}
