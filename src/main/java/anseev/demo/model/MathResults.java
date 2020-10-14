package anseev.demo.model;


import anseev.demo.enums.MathType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MathResults {
    @Id
    @GeneratedValue
    Long id;
    @Enumerated
    MathType mathType;
    Double result;
}
