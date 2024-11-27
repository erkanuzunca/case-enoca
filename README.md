# E-Ticaret Sepet ve Sipariş Yönetim Sistemi

Bu proje, bir e-ticaret platformu için sepete ürün ekleme, sipariş oluşturma ve ürün yönetimi gibi temel işlevleri sağlayan bir **Spring Boot** tabanlı uygulamadır. Uygulama, ürünler, müşteriler, sepetler ve siparişler arasındaki ilişkileri yönetmektedir. Ayrıca, stok takibi, sepete ürün ekleyip çıkarma gibi özellikler de içerir.

## Proje Özeti

Bu projede aşağıdaki temel işlevler bulunmaktadır:

- **Sepet Yönetimi**: Müşteriler sepetlerine ürün ekleyebilir, çıkarabilir, miktarlarını değiştirebilirler.
- **Sipariş Yönetimi**: Müşteriler sipariş verebilir ve siparişleri takip edebilirler.
- **Ürün Yönetimi**: Adminler ürün ekleyebilir, güncelleyebilir veya silebilirler.
- **Stok Takibi**: Ürünlerin stokları takip edilir ve stok bitiminde satışa kapatılır.

## Kullanılan Teknolojiler

- **Java 17+**
- **Spring Boot** (REST API geliştirme)
- **H2 Database** (Geçici veritabanı olarak geliştirme aşamasında kullanılır, üretim için PostgreSQL veya başka bir veritabanı kullanılabilir)
- **JPA/Hibernate** (Veritabanı işlemleri)
- **Lombok** (Kodun daha kısa ve anlaşılır olması için)
- **Postman** (API testleri için)
  
## Başlangıç

### Gerekli Kurulumlar

1. **Java 17+** yüklenmiş olmalıdır.
2. **Maven** veya **Gradle** ile bağımlılıklar yönetilmektedir.
3. Test için POSTMAN kullanabilirsiniz.
   
 AddCustomer servisi için postman sorgusu : POST http://localhost:8080/customers 
 Body
{
  "name": "x",
  "email": "x@example.com"
}

GetProduct
-GetProduct servisi için postman sorgusu : GET http://localhost:8080/products/{Product id}

CreateProduct
-CreateProduct servisi için postman sorgusu : POST http://localhost:8080/products
Body
{
  "name": "Laptop",
  "price": 1100.00,
  "stockQuantity": 10
}

UpdateProduct
-UpdateProduct servisi için postman sorgusu : PUT http://localhost:8080/products/{Product id}
Body
{
  "name": "Laptop",
  "price": 1700.00,
  "stockQuantity": 10
}

DeleteProduct
-DeleteProduc servisi için postman sorgusu : DELETE http://localhost:8080/products/{Product id}

GetCart
-GetCart servisi için postman sorgusu : GET http://localhost:8080/carts/{Customer id}

UpdateCart
-UpdateCart servisi için postman sorgusu : PUT http://localhost:8080/carts/{Customer id}/update

EmptyCart
-EmptyCart servisi için postman sorgusu : DELETE http://localhost:8080/carts/{Customer id}/empty

PlaceOrder
-PlaceOrder servisi için postman sorgusu : POST http://localhost:8080/orders/{Customer id}/place

GetOrderForCode
-GetOrderForCode servisi için postman sorgusu : GET http://localhost:8080/orders/{Customer id}

GetAllOrdersForCustomer
-GetAllOrdersForCustomer servisi için postman sorgusu : GET http://localhost:8080/orders/customers/{Customer id}

AddProductToCart
-AddProductToCart servisi için postman sorgusu : POST http://localhost:8080/carts/{customerId}/products/{productId}/add

RemoveProductFromCart
-RemoveProductFromCart servisi için postman sorgusu : DELETE http://localhost:8080/carts/{customerId}/remove

