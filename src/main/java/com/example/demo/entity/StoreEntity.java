
@Entity
public class StoreEntity{
     @id
     @Generatedvalue(strategy=GenerationType.IDENTITY)
     private long id;
     @Column(unique=true)
     private String storeName;
     private String address;
     private String region;
     private Boolean active=true;
     
}