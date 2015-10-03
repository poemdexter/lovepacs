package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoxJson {

    @JsonProperty private Integer id;
    @JsonProperty private String name;
    @JsonProperty private List<ContentJson> contents;

}
