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
3. Test için **Postman** kullanabilirsiniz.

### API Servisleri

#### 1. **AddCustomer**
   - **Yöntem**: `POST`
   - **URL**: `http://localhost:8080/customers`
   - **Body**:
     ```json
     {
       "name": "x",
       "email": "x@example.com"
     }
     ```

#### 2. **GetProduct**
   - **Yöntem**: `GET`
   - **URL**: `http://localhost:8080/products/{Product id}`

#### 3. **CreateProduct**
   - **Yöntem**: `POST`
   - **URL**: `http://localhost:8080/products`
   - **Body**:
     ```json
     {
       "name": "Laptop",
       "price": 1100.00,
       "stockQuantity": 10
     }
     ```

#### 4. **UpdateProduct**
   - **Yöntem**: `PUT`
   - **URL**: `http://localhost:8080/products/{Product id}`
   - **Body**:
     ```json
     {
       "name": "Laptop",
       "price": 1700.00,
       "stockQuantity": 10
     }
     ```

#### 5. **DeleteProduct**
   - **Yöntem**: `DELETE`
   - **URL**: `http://localhost:8080/products/{Product id}`

#### 6. **GetCart**
   - **Yöntem**: `GET`
   - **URL**: `http://localhost:8080/carts/{Customer id}`

#### 7. **UpdateCart**
   - **Yöntem**: `PUT`
   - **URL**: `http://localhost:8080/carts/{Customer id}/update`

#### 8. **EmptyCart**
   - **Yöntem**: `DELETE`
   - **URL**: `http://localhost:8080/carts/{Customer id}/empty`

#### 9. **PlaceOrder**
   - **Yöntem**: `POST`
   - **URL**: `http://localhost:8080/orders/{Customer id}/place`

#### 10. **GetOrderForCode**
   - **Yöntem**: `GET`
   - **URL**: `http://localhost:8080/orders/{Customer id}`

#### 11. **GetAllOrdersForCustomer**
   - **Yöntem**: `GET`
   - **URL**: `http://localhost:8080/orders/customers/{Customer id}`

#### 12. **AddProductToCart**
   - **Yöntem**: `POST`
   - **URL**: `http://localhost:8080/carts/{customerId}/products/{productId}/add`

#### 13. **RemoveProductFromCart**
   - **Yöntem**: `DELETE`
   - **URL**: `http://localhost:8080/carts/{customerId}/remove`
