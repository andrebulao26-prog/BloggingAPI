# Blogging API
A a simple RESTful API with basic CRUD operations for a personal blogging platform. CRUD stands for Create, Read, Update, and Delete.   
Uses Springboot, postgreSQL, and Docker. 
## Requirements
postgreSQL
Docker
## Installation
Clone the repository: https://github.com/andrebulao26-prog/BloggingAPI.git  
Set your PostgreSQL username, database name, and password in the Application properties and compose yaml
## Usage

Create Blog Post (Make sure your post body has all of the following fields)
```
POST /posts
{
  "title": "My First Blog Post",
  "content": "This is the content of my first blog post.",
  "category": "Technology",
  "tags": ["Tech", "Programming"]
}
```
Update Blog Post
```
PUT /posts/[id]
{
  "title": "My Updated Blog Post",
  "content": "This is the updated content of my first blog post.",
  "category": "Technology",
  "tags": ["Tech", "Programming"]
}
```
Delete Blog Post
```
DELETE /posts/[id]
```
Get All Blog Posts
```
GET /posts
```
Get Blog Posts with a term (Will only return posts with the term found in the title, content, category, or tags)
```
GET /posts/?term=[term]
```
[https://roadmap.sh/projects/blogging-platform-api](https://roadmap.sh/projects/blogging-platform-api)
