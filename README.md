# Blog-Platform-Backend

A full-featured blogging platform backend built with Spring Boot, Spring Security, JWT Authentication, MySQL, Cloudinary, Swagger/OpenAPI, and Spring AI (Gemini Integration).

## Features

### Authentication & Authorization

* User Registration
* User Login
* JWT-based Authentication
* Role-based Authorization
* Secure Password Encryption using BCrypt

### Blog Management

* Create Blog Posts
* Update Blog Posts
* Delete Blog Posts
* View Blog Details
* View All Blogs
* Slug-based Blog Retrieval

### User Profile

* View User Profile
* Update User Profile
* User-specific Blog Listing

### Categories & Tags

* Create Categories
* Assign Categories to Blogs
* Create Tags
* Assign Multiple Tags to Blogs

### Engagement Features

* Like Blogs
* Unlike Blogs
* Comment on Blogs
* Delete Comments
* View Blog Comments

### Search & Discovery

* Search Blogs by Title
* Popular Blogs based on Likes

### Media Upload

* Image Upload using Cloudinary
* Cloud-based Media Storage

### AI Features

* AI Blog Content Generation
* Gemini API Integration
* Spring AI Support

### API Documentation

* Swagger UI Integration
* OpenAPI Documentation

---

## Tech Stack

### Backend

* Java 21
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Hibernate
* JWT

### Database

* MySQL

### Cloud Services

* Cloudinary

### AI

* Spring AI
* Google Gemini

### Documentation

* Swagger / OpenAPI

### Build Tool

* Maven

### Deployment

* Docker
* Render / TiDB

---

## Project Structure

```text
src
├── controller
├── service
│   ├── impl
├── repository
├── entity
├── dto
├── security
├── config
└── util
```

---

## Environment Variables

Create a `.env` file:

```env
DB_URL=
DB_USERNAME=
DB_PASSWORD=

JWT_SECRET=
JWT_EXPIRATION=

CLOUDINARY_CLOUD_NAME=
CLOUDINARY_API_KEY=
CLOUDINARY_API_SECRET=

GEMINI_API_KEY=
```

---

## Running Locally

### Clone Repository

```bash
git clone https://github.com/Ashu9507/Blog-Platform-Backend.git
cd Blog-Platform-Backend
```

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

Application runs on:

```text
http://localhost:8080
```

---

## Swagger Documentation

After starting the application:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI Docs:

```text
http://localhost:8080/v3/api-docs
```

---

## Docker Support

dockerfile

## API Modules

### Auth APIs

```text
POST /api/auth/register
POST /api/auth/login
```

### Blog APIs

```text
POST /api/blogs
GET  /api/blogs
GET  /api/blogs/{id}
PUT  /api/blogs/{id}
DELETE /api/blogs/{id}
GET  /api/blogs/search
```

### Comment APIs

```text
POST /api/comments/{blogId}
DELETE /api/comments/{commentId}
GET /api/comments/blog/{blogId}
```

### Like APIs

```text
POST /api/likes/{blogId}
DELETE /api/likes/{blogId}
GET /api/likes/{blogId}
```

### AI APIs

```text
POST /api/ai/generate-blog
POST /api/ai/generate-title
POST /api/ai/summarize
POST /api/ai/seo-tags
```
### Upload APIs
```text
POST /api/upload
```

### UserProfile APIs
```text
GET /api/users/profile
PUT /api/users/profile
GET /api/users/{id}
```
---

## Future Enhancements

* Bookmark Blogs
* Follow Authors
* Reading History
* Blog Analytics Dashboard
* Notifications
* AI Blog Summarization
* AI SEO Optimization
* AI Tag Suggestions
* Rich Text Editor Support

---

## Author

Ashutosh Kumar

GitHub:
https://github.com/Ashu9507

---

## License

This project is licensed under the MIT License.
