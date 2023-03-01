package com.example.techmasterpi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter


public class OffreFavorit {


        @Id
        @GeneratedValue(strategy =

                GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "offer_id")
        private SellerOffer sellerOffer;
    private LocalDateTime timestamp;

    private String action;

    public OffreFavorit(Long id, SellerOffer sellerOffer,LocalDateTime timestamp, String action) {
        this.id = id;
        this.sellerOffer = sellerOffer;

        this.timestamp = timestamp;
        this.action = action;
    }


    public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }


}
