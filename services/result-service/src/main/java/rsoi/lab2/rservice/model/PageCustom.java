package rsoi.lab2.rservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageCustom<T> extends PageImpl<T> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PageCustom(@JsonProperty("content") List<T> content,
                      @JsonProperty("number") int number,
                      @JsonProperty("size") int size,
                      @JsonProperty("totalElements") Long totalElements,
                      @JsonProperty("pageable") JsonNode pageable,
                      @JsonProperty("last") boolean last,
                      @JsonProperty("totalPages") int totalPages,
                      @JsonProperty("sort") JsonNode sort,
                      @JsonProperty("first") boolean first,
                      @JsonProperty("numberOfElements") int numberOfElements) {

        super(content, PageRequest.of(number, size), totalElements);
    }

    public PageCustom(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PageCustom(List<T> content) {
        super(content);
    }

    public PageCustom() {
        super(new ArrayList<>());
    }
}
