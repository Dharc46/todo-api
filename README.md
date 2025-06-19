# 🚀 Todo List REST API - Spring Boot

Ứng dụng To-Do List RESTful API được phát triển với Spring Boot, hỗ trợ đầy đủ các thao tác CRUD và quản lý trạng thái công việc.

## 📂 Cấu trúc Project (Cập nhật)

```bash
todo-api/
├── postman/ # Thư mục chứa Postman collection
│ └── Todo-API-Collection.json
├── src/
│ └── main/
│ ├── java/
│ │ └── com/
│ │ └── todoapi/
│ │ ├── controller/ # Các class điều khiển API
│ │ │ └── TaskController.java
│ │ ├── dto/ # Data Transfer Objects
│ │ │ ├── TaskCreateDTO.java
│ │ │ ├── TaskResponseDTO.java
│ │ │ ├── TaskStatusUpdateDTO.java
│ │ │ └── TaskUpdateDTO.java
│ │ ├── exception/ # Xử lý ngoại lệ
│ │ ├── model/ # Entity model
│ │ │ └── Task.java
│ │ ├── repository/ # Database repository
│ │ │ └── TaskRepository.java
│ │ ├── service/ # Business logic
│ │ │ ├── TaskService.java
│ │ │ └── TaskServiceImpl.java
│ │ └── TodoApiApplication.java # Main class
│ └── resources/
│ ├── application.yml # Cấu hình ứng dụng
│ └── data.sql # Dữ liệu khởi tạo (nếu có)
├── target/ # Thư mục build output
├── pom.xml # File cấu hình Maven
└── README.md # Tài liệu hướng dẫn
```


## 🌐 API Endpoints

| Method | Endpoint | Mô tả |
|--------|----------|-------|
| `GET` | `/api/tasks` | Lấy danh sách tất cả tasks |
| `POST` | `/api/tasks` | Tạo task mới |
| `GET` | `/api/tasks/{id}` | Lấy task theo ID |
| `PUT` | `/api/tasks/{id}` | Cập nhật toàn bộ task |
| `PATCH` | `/api/tasks/{id}/status` | Cập nhật trạng thái task |
| `DELETE` | `/api/tasks/{id}` | Xóa task |
| `GET` | `/api/tasks/status/{status}` | Lọc tasks theo trạng thái |

## 🛠️ Cài đặt & Chạy ứng dụng

```bash
# Clone repository
git clone <repository-url>
cd todo-api

# Build project
mvn clean install

# Chạy ứng dụng (development mode)
mvn spring-boot:run
```

⚙️ Cấu hình ứng dụng (application.yml)

```bash
spring:
  application:
    name: todo-api
  
  datasource:
    url: jdbc:h2:mem:tododb
    username: sa
    password: 
  
  h2:
    console:
      enabled: true
      path: /h2-console
  
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true

server:
  port: 8080
```

🔍 Test API với Postman

1. Tạo task mới

```bash
POST http://localhost:8080/api/tasks
Content-Type: application/json

{
    "title": "Học Spring Boot",
    "description": "Hoàn thành tutorial REST API",
    "priority": "HIGH"
}
```

2. Cập nhật trạng thái (sử dụng PATCH)

```bash
PATCH http://localhost:8080/api/tasks/1/status
Content-Type: application/json

{
    "status": "IN_PROGRESS"
}
```

3. Lấy task theo trạng thái

```bash
GET http://localhost:8080/api/tasks/status/COMPLETED
```

## 📚 Kiến trúc ứng dụng

1. **Controller Layer** (`controller/`)
   - Xử lý HTTP requests/responses
   - Định nghĩa API endpoints
   - Map request data vào DTO
   - Trả về response phù hợp

2. **Service Layer** (`service/`)
   - Chứa business logic chính
   - Interface (`TaskService.java`) và Implementation (`TaskServiceImpl.java`)
   - Xử lý validation và nghiệp vụ

3. **Repository Layer** (`repository/`)
   - Giao tiếp với database
   - Kế thừa `JpaRepository`
   - Xử lý truy vấn dữ liệu

4. **DTO Layer** (`dto/`)
   - `TaskCreateDTO`: DTO cho tạo mới task
   - `TaskUpdateDTO`: DTO cho cập nhật task
   - `TaskStatusUpdateDTO`: DTO riêng cho cập nhật trạng thái
   - `TaskResponseDTO`: DTO trả về cho client

5. **Model Layer** (`model/`)
   - Entity class với JPA annotations
   - Định nghĩa quan hệ database
   - Getter/Setter methods

✨ Tính năng chính

✅ Full CRUD Operations với RESTful API

✅ DTO Pattern cho các loại request/response khác nhau

✅ PATCH Endpoint riêng cho cập nhật trạng thái

✅ H2 Database Console tích hợp

✅ Lọc theo trạng thái với endpoint chuyên biệt

✅ Layered Architecture rõ ràng

🔜 Tính năng mở rộng
- Thêm xác thực với Spring Security

- Triển khai PostgreSQL cho production

- Thêm phân trang cho GET /api/tasks

- Tích hợp Swagger UI

- Unit tests với JUnit và Mockito

- Dockerize ứng dụng

🔗 Truy cập nhanh:

- Local API: http://localhost:8080/api/tasks

- H2 Console: http://localhost:8080/h2-console

- Postman Collection: postman/Todo-API-Collection.json