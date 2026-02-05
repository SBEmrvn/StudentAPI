# Question 2: Student Registration API

## Project Description
A RESTful API for student registration and information management built with Spring Boot.

## Technologies Used
- Java 17/21
- Spring Boot 3.2.2
- Maven
- Spring Web

## Project Structure
```
src/main/java/com/student/question2studentapi/
├── controller/
│   └── student/
│       └── StudentController.java
├── model/
│   └── student/
│       └── Student.java
└── Question2StudentApiApplication.java
```

## How to Run the Application

### Prerequisites
- Java JDK 17 or higher
- Maven (included via Maven Wrapper)

### Steps to Run
1. Clone the repository
2. Navigate to project directory:
   ```bash
   cd question2-student-api
   ```
3. Run the application:
   
   **Windows:**
   ```bash
   .\mvnw.cmd spring-boot:run
   ```
   
   **Mac/Linux:**
   ```bash
   ./mvnw spring-boot:run
   ```

4. The application will start on `http://localhost:8080`

## API Endpoints

### 1. Get All Students
- **URL:** `/api/students`
- **Method:** `GET`
- **Status Code:** `200 OK`
- **Description:** Retrieves all registered students

**Sample Response:**
```json
[
  {
    "studentId": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@university.edu",
    "major": "Computer Science",
    "gpa": 3.8
  },
  {
    "studentId": 2,
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane.smith@university.edu",
    "major": "Engineering",
    "gpa": 3.9
  }
]
```

---

### 2. Get Student by ID
- **URL:** `/api/students/{studentId}`
- **Method:** `GET`
- **Status Codes:** 
  - `200 OK` - Student found
  - `404 NOT FOUND` - Student not found

**Sample Request:**
```
GET http://localhost:8080/api/students/1
```

**Sample Response (200 OK):**
```json
{
  "studentId": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@university.edu",
  "major": "Computer Science",
  "gpa": 3.8
}
```

---

### 3. Get Students by Major
- **URL:** `/api/students/major/{major}`
- **Method:** `GET`
- **Status Code:** `200 OK`
- **Description:** Retrieves all students in a specific major (case-insensitive)

**Sample Request:**
```
GET http://localhost:8080/api/students/major/Computer Science
```

**Sample Response:**
```json
[
  {
    "studentId": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@university.edu",
    "major": "Computer Science",
    "gpa": 3.8
  },
  {
    "studentId": 3,
    "firstName": "Michael",
    "lastName": "Johnson",
    "email": "michael.j@university.edu",
    "major": "Computer Science",
    "gpa": 3.5
  }
]
```

---

### 4. Filter Students by Minimum GPA
- **URL:** `/api/students/filter?gpa={minGpa}`
- **Method:** `GET`
- **Status Code:** `200 OK`
- **Description:** Filters students with GPA greater than or equal to the specified minimum

**Sample Request:**
```
GET http://localhost:8080/api/students/filter?gpa=3.7
```

**Sample Response:**
```json
[
  {
    "studentId": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@university.edu",
    "major": "Computer Science",
    "gpa": 3.8
  },
  {
    "studentId": 2,
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane.smith@university.edu",
    "major": "Engineering",
    "gpa": 3.9
  }
]
```

---

### 5. Register New Student
- **URL:** `/api/students`
- **Method:** `POST`
- **Status Code:** `201 CREATED`
- **Content-Type:** `application/json`

**Sample Request:**
```json
POST http://localhost:8080/api/students
Content-Type: application/json

{
  "firstName": "Sarah",
  "lastName": "Davis",
  "email": "sarah.d@university.edu",
  "major": "Mathematics",
  "gpa": 3.95
}
```

**Sample Response (201 CREATED):**
```json
{
  "studentId": 6,
  "firstName": "Sarah",
  "lastName": "Davis",
  "email": "sarah.d@university.edu",
  "major": "Mathematics",
  "gpa": 3.95
}
```

---

### 6. Update Student Information
- **URL:** `/api/students/{studentId}`
- **Method:** `PUT`
- **Status Codes:**
  - `200 OK` - Successfully updated
  - `404 NOT FOUND` - Student not found
- **Content-Type:** `application/json`

**Sample Request:**
```json
PUT http://localhost:8080/api/students/1
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe.updated@university.edu",
  "major": "Computer Science",
  "gpa": 3.9
}
```

**Sample Response (200 OK):**
```json
{
  "studentId": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe.updated@university.edu",
  "major": "Computer Science",
  "gpa": 3.9
}
```

---

## Testing the API

### Using Browser
For GET requests, you can use your browser:
- `http://localhost:8080/api/students`
- `http://localhost:8080/api/students/1`
- `http://localhost:8080/api/students/major/Engineering`
- `http://localhost:8080/api/students/filter?gpa=3.5`

### Using Postman
1. Import the provided Postman collection
2. Run each request
3. Verify the responses match the samples above

### Using cURL

**Get All Students:**
```bash
curl http://localhost:8080/api/students
```

**Get Student by ID:**
```bash
curl http://localhost:8080/api/students/1
```

**Get Students by Major:**
```bash
curl http://localhost:8080/api/students/major/Engineering
```

**Filter by GPA:**
```bash
curl "http://localhost:8080/api/students/filter?gpa=3.7"
```

**Register New Student:**
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Sarah","lastName":"Davis","email":"sarah.d@university.edu","major":"Mathematics","gpa":3.95}'
```

**Update Student:**
```bash
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"firstName":"John","lastName":"Doe","email":"john.updated@university.edu","major":"Computer Science","gpa":3.9}'
```

---

## Sample Data
The application initializes with 5 sample students:

1. John Doe - Computer Science - GPA: 3.8
2. Jane Smith - Engineering - GPA: 3.9
3. Michael Johnson - Computer Science - GPA: 3.5
4. Emily Brown - Business - GPA: 3.7
5. David Wilson - Engineering - GPA: 3.2

---

## Key Differences from Question 1

### New Endpoint: PUT (Update)
- Updates existing student information
- Requires complete student object in request body
- Keeps the same studentId

### Path Variable for Major
- Uses `@PathVariable` instead of `@RequestParam`
- URL: `/api/students/major/Engineering` (not `?major=Engineering`)

### GPA Filtering
- Uses `@RequestParam` for query parameter
- Filters students with GPA >= specified value

---

## HTTP Status Codes Used

| Code | Meaning | Used For |
|------|---------|----------|
| **200 OK** | Success | GET and PUT requests |
| **201 CREATED** | Resource created | POST (register student) |
| **404 NOT FOUND** | Resource not found | GET/PUT when student doesn't exist |

---

## Author
[Your Name]  
[Your Student ID]

## Date
February 4, 2026