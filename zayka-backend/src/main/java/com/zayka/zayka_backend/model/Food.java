package com.zayka.zayka_backend.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("foods")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Food {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
}
