
@Entity
public class StoreEntity{
     @id
     @Generatedvalue(strategy=GenerationType.IDENTITY)
     private Long id;
     @Column(unique=true)
     private String storeName;
     private String address;
     private String region;
     private Boolean active=true;
     

     public void setId(Long id){
        this.id=id;
     }
     public Long getId(){
        return id;
     }

     public void setStoreName(String storename){
        this.str
     }

}