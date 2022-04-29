package com.rzerosystems.marathonapp.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "marathonID",
        "createdBy",
        "bib",
        "firstName",
        "lastName",
        "city",
        "state",
        "country",
        "time",
        "splits",
        "gender",
        "age",
        "division"
})
public class Result {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("marathonID")
    private Integer marathonID;
    @JsonProperty("createdBy")
    private String createdBy;
    @NotNull(message = "The bib is required, Must be unique for marathon.")
    @JsonProperty("bib")
    private String bib;
    @NotNull(message = "The first name is required.")
    @JsonProperty("firstName")
    private String firstName;
    @NotNull(message = "The last name is required.")
    @JsonProperty("lastName")
    private String lastName;
    @NotNull(message = "The city is required.")
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @NotNull(message = "The country is required.")
    @JsonProperty("country")
    private String country;
    @NotNull(message = "The time is required.")
    @JsonProperty("time")
    private String time;
    @NotNull(message = "The splits are required.")
    @JsonProperty("splits")
    private List<String> splits;
    @NotNull(message = "The gender is required.")
    @JsonProperty("gender")
    private String gender;
    @NotNull(message = "The age is required.")
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("division")
    private Integer division;

}