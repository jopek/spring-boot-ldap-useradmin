package org.springframework.ldap.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Name;
import java.io.IOException;

public class NameSerializer extends JsonSerializer<Name> {
  Logger LOG = LoggerFactory.getLogger(NameSerializer.class);

  public NameSerializer() {
  }

  @Override
  public void serialize(Name value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
    jgen.writeString(value.toString());
  }

  @Override
  public Class<Name> handledType() {
    return Name.class;
  }
}
