# Question 2: Student-Registration-API

## Project Description
A RESTful API for student registration and information management built with Spring Boot.

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



1. The application will start on `http://localhost:8080`

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


### Using Postman
1. Import the provided Postman collection
2. Run each request
3. Verify the responses match the samples above

### Using cURL

**Get All Students:**
```bash
 http://localhost:8080/api/students
```

**Get Student by ID:**
```bash
http://localhost:8080/api/students/1
```

**Get Students by Major:**
```bash

 http://localhost:8080/api/students/major/Engineering
```

**Filter by GPA:**
```bash
"http://localhost:8080/api/students/filter?gpa=3.7"
```

**Register New Student:**
```bash
 -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Sarah","lastName":"Davis","email":"sarah.d@university.edu","major":"Mathematics","gpa":3.95}'
```

**Update Student:**
```bash
 -X PUT http://localhost:8080/api/students/1 \
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
SCREENSHOTS FOR OUTPUT 
---
<img width="1801" height="498" alt="Screenshot 2026-02-05 143829" src="https://github.com/user-attachments/assets/75c47da4-f6b6-4666-8c2c-62008cf721a3" />

---
<img width="1913" height="455" alt="Screenshot 2026-02-05 144053" src="https://github.com/user-attachments/assets/f3d5892a-0d64-4b62-81f8-cd64bd2220b4" />

---
<img width="1919" height="478" alt="Screenshot 2026-02-05 144105" src="https://github.com/user-attachments/assets/e90dd644-d784-4376-ab8a-3559da1b7366" />

---
<img width="1919" height="932" alt="Screenshot 2026-02-05 144114" src="https://github.com/user-attachments/assets/fbdd772d-4667-485b-995f-12e4c4a6f859" />

---
<img width="1919" height="845" alt="Screenshot 2026-02-05 144213" src="https://github.com/user-attachments/assets/af15e026-5a3f-44f7-bab4-e80a4e8dd563" />

---
<img width="1911" height="975" alt="Screenshot 2026-02-05 144248" src="https://github.com/user-attachments/assets/5320916b-f193-4c4e-9ace-1c436b813564" />

---
<img width="1915" height="975" alt="Screenshot 2026-02-05 144338" src="https://github.com/user-attachments/assets/87fce625-7f8f-4ee1-8312-6b37d8db564c" />

---
<img width="1919" height="972" alt="Screenshot 2026-02-05 144420" src="https://github.com/user-attachments/assets/88308660-5e9f-461c-9936-fa48d1293188" />

---
<img width="1742" height="912" alt="Screenshot 2026-02-05 144752" src="https://github.com/user-attachments/assets/dc184f9e-025a-41fe-a618-8c8cde7016d0" />

---
<img width="1808" height="951" alt="Screenshot 2026-02-05 145408" src="https://github.com/user-attachments/assets/9006817e-2fa6-40b1-9bb5-6da6b2b20ca2" />

---
<img width="1919" height="1074" alt="Screenshot 2026-02-05 150050" src="https://github.com/user-attachments/assets/194caf4d-6c62-463f-abd1-6a61028e54d0" />

---
## Author
SHEDRICK BUCAGU ELISA  
26939

## Date
February 4, 2026
