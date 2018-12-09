package alararestaurant.domain.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public enum OrderType {
    ForHere, ToGo;
}
