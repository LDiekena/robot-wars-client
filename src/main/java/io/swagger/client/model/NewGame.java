/*
 * Robot Wars API
 * Eine Api für das Spiel Robot Wars
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * NewGame
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2025-01-24T08:58:18.098763241Z[GMT]")

public class NewGame {
  @SerializedName("mapId")
  private String mapId = null;

  public NewGame mapId(String mapId) {
    this.mapId = mapId;
    return this;
  }

   /**
   * Die Map ID für die Auswahl der Karte
   * @return mapId
  **/
  @Schema(example = "1234", required = true, description = "Die Map ID für die Auswahl der Karte")
  public String getMapId() {
    return mapId;
  }

  public void setMapId(String mapId) {
    this.mapId = mapId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewGame newGame = (NewGame) o;
    return Objects.equals(this.mapId, newGame.mapId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mapId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewGame {\n");
    
    sb.append("    mapId: ").append(toIndentedString(mapId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
