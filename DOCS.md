# Dokumentacja API – Spring Boot Student Management

## Base URL

`http://localhost:8080/`

---

# Students API

## Pobierz wszystkich studentów

### GET `/students`

#### Response
```json
[
  {
    "id": 1,
    "firstName": "Ola",
    "age": 21,
    "city": "DOA"
  }
]
```

---

## Pobierz studenta po ID

### GET `/students/{id}`

#### Example
`GET /students/1`

---

## Dodaj studenta

### POST `/students`

#### Request Body
```json
{
  "firstName": "Ola",
  "age": 21,
  "city": "DOA"
}
```

---

## Zaktualizuj studenta

### PUT `/students/{id}`

#### Example
`PUT /students/1`

#### Request Body
```json
{
  "firstName": "Ł",
  "age": 21,
  "city": "Go"
}
```

---

## Usuń studenta

### DELETE `/students/{id}`

#### Example
`DELETE /students/1`

---

# Courses API

## Pobierz wszystkie kursy

### GET `/courses`

---

## Pobierz kurs po ID

### GET `/courses/{id}`

#### Example
`GET /courses/1`

---

## Dodaj kurs

### POST `/courses`

#### Request Body
```json
{
  "name": "cooked2",
  "ects": 5
}
```

---

## Zaktualizuj kurs

### PUT `/courses/{id}`

#### Example
`PUT /courses/1`

#### Request Body
```json
{
  "name": "Wróć",
  "ects": 5
}
```

---

## Usuń kurs

### DELETE `/courses/{id}`

#### Example
`DELETE /courses/1`

---

# Grades API

## Pobierz wszystkie oceny

### GET `/grades`

---

## Pobierz ocenę po ID

### GET `/grades/{id}`

#### Example
`GET /grades/1`

---

## Dodaj ocenę

### POST `/grades`

#### Request Body
```json
{
  "grade": 5.0,
  "student": {
    "id": 1
  },
  "course": {
    "id": 1
  }
}
```

---

## Zaktualizuj ocenę

### PUT `/grades/{id}`

#### Example
`PUT /grades/1`

#### Request Body
```json
{
  "grade": 5.5
}
```

---

## Usuń ocenę

### DELETE `/grades/{id}`

#### Example
`DELETE /grades/1`

---

# Endpointy biznesowe

## Średnia GPA studenta

### GET `/students/{id}/gpa`

#### Example
`GET /students/1/gpa`

#### Description
Zwraca średnią ocen (GPA) wybranego studenta.

---

## Liczba studentów

### GET `/students/count`

#### Description
Zwraca całkowitą liczbę studentów w bazie.

---

## Oceny studenta

### GET `/students/{id}/grades`

#### Example
`GET /students/1/grades`

#### Description
Zwraca wszystkie oceny przypisane do danego studenta.

---

## Studenci którzy nie zaliczyli kursu

### GET `/courses/{id}/failed`

#### Example
`GET /courses/1/failed`

#### Description
Zwraca listę studentów, którzy nie zaliczyli danego kursu.

---

## Kursy po liczbie ECTS

### GET `/courses/ects/{id}`

#### Example
`GET /courses/ects/5`

#### Description
Zwraca kursy o określonej liczbie punktów ECTS.

---

## Najlepsi studenci

### GET `/students/top`

#### Description
Zwraca studentów ze średnią powyżej 4.75.

---

# Kody odpowiedzi HTTP

| Kod | Znaczenie |
|------|------------|
| 200 | OK |
| 201 | Created |
| 400 | Bad Request |
| 404 | Not Found |
| 500 | Internal Server Error |

---

# Technologie

- Spring Boot
- REST API
- JSON
- Hibernate / JPA
