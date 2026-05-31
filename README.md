# API RESTful Bảo Mật với Spring Boot và MongoDB

Dự án này minh họa cách triển khai API RESTful bảo mật sử dụng Spring Boot với MongoDB làm cơ sở dữ liệu.

## Yêu Cầu Hệ Thống

- JDK 8
- Gradle
- MongoDB
- IDE yêu thích của bạn (IntelliJ IDEA, Eclipse, etc.)

## Công Nghệ Sử Dụng

- Spring Boot 2.7.0
- Spring Security
- Spring Data MongoDB
- JWT (JSON Web Tokens)
- Gradle
- Thymeleaf (cho giao diện web)
- Bootstrap 5 (cho UI)

## Cấu Trúc Dự Án

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           ├── controller/     # Các controller REST và controller view
│   │           ├── model/          # Các model dữ liệu
│   │           ├── repository/     # Các repository MongoDB
│   │           ├── security/       # Cấu hình bảo mật và tiện ích JWT
│   │           ├── service/        # Các service xử lý nghiệp vụ
│   │           └── SecureApiApplication.java
│   └── resources/
│       ├── static/                 # Tài nguyên tĩnh (CSS, JS)
│       ├── templates/              # Các template Thymeleaf
│       └── application.properties  # Cấu hình ứng dụng
```

## Giải Thích Các Thành Phần

### 1. Controllers
- `AuthController`: Xử lý xác thực người dùng (đăng nhập/đăng ký)
- `TestController`: Chứa các endpoint kiểm tra cho các vai trò người dùng khác nhau
- `ViewController`: Quản lý định tuyến trang web

### 2. Models
- `User`: Entity người dùng với tích hợp Spring Security

### 3. Security
- `WebSecurityConfig`: Cấu hình bảo mật chính
- `JwtUtils`: Tạo và xác thực token JWT
- `AuthTokenFilter`: Bộ lọc xác thực JWT

### 4. Services
- `UserDetailsServiceImpl`: Service chi tiết người dùng tùy chỉnh cho Spring Security

### 5. Repositories
- `UserRepository`: Repository MongoDB cho các thao tác người dùng

## Cài Đặt & Thiết Lập

1. Clone repository
2. Đảm bảo MongoDB đang chạy trên localhost:27017
3. Cấu hình application.properties:
   ```properties
   spring.data.mongodb.host=localhost
   spring.data.mongodb.port=27017
   spring.data.mongodb.database=secureapi
   jwt.secret=yourSecretKey
   jwt.expiration=86400000
   server.port=8080
   ```
4. Chạy ứng dụng:
   ```bash
   ./gradlew bootRun
   ```
   Run java SecureApiApplication.java

## Tính Năng Bảo Mật

- Xác thực dựa trên JWT
- Mã hóa mật khẩu sử dụng BCrypt
- Phân quyền dựa trên vai trò (USER, ADMIN)
- Bảo mật endpoint với Spring Security
- Bảo mật tài liệu MongoDB
- Cấu hình CORS
- Quản lý phiên làm việc

## Tài Liệu API

API sẽ có sẵn tại `http://localhost:8080`

### Endpoint Xác Thực:
- POST /api/auth/signup - Đăng ký người dùng mới
- POST /api/auth/login - Đăng nhập người dùng

### Endpoint Kiểm Tra:
- GET /api/test/all - Truy cập công khai
- GET /api/test/user - Yêu cầu vai trò USER
- GET /api/test/admin - Yêu cầu vai trò ADMIN

### Trang Web:
- / - Trang chủ
- /login - Trang đăng nhập
- /register - Trang đăng ký
- /dashboard - Bảng điều khiển người dùng (yêu cầu xác thực)
- /admin - Bảng điều khiển admin (yêu cầu vai trò admin)

## Kiểm Thử

Chạy kiểm thử bằng lệnh:
```bash
./gradlew test
```

## Phát Triển

1. Dự án sử dụng Gradle để quản lý phụ thuộc
2. Các phụ thuộc chính được định nghĩa trong build.gradle
3. Cấu hình ứng dụng có thể được thiết lập trong application.properties hoặc application.yml
4. Cấu hình bảo mật nằm trong WebSecurityConfig.java
5. Cấu hình JWT nằm trong application.properties

## Thực Hành Bảo Mật Tốt Nhất

1. Tất cả mật khẩu được mã hóa bằng BCrypt
2. Sử dụng token JWT cho xác thực không trạng thái
3. Triển khai kiểm soát truy cập dựa trên vai trò
4. Cấu hình CORS đúng cách
5. Quản lý phiên làm việc không trạng thái
6. Triển khai bảo mật MongoDB 