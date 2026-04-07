# 📘 Aluno Online 

## 📖 Visão Geral
Este projeto consiste no desenvolvimento de uma **API REST** utilizando **Spring Boot**, com o objetivo de gerenciar dados de **Alunos** e **Professores**.  

A aplicação implementa operações do tipo **CRUD** (Create, Read, Update e Delete), permitindo:  
- Cadastro de registros  
- Consulta de dados  
- Atualização de informações  
- Remoção de dados  

---

## 🛠️ Tecnologias Utilizadas
- Java 21 (Corretto JDK)  
- Spring Boot 4.0.3  
- Spring Data JPA  
- Maven  
- Lombok  
- PostgreSQL  
- Insomnia (testes de API)  
- DBeaver (visualização do banco)  

---

## 🧱 Arquitetura do Projeto
A aplicação segue o padrão **em camadas**:

- **Model** → Entidades JPA (`Aluno`, `Professor`)  
- **Repository** → Interfaces que estendem `JpaRepository`  
- **Service** → Implementa a lógica CRUD básica  
- **Controller** → Expõe os endpoints REST para consumo externo  

Fluxo resumido:
```
Cliente (Insomnia) → Controller → Service → Repository → Banco (PostgreSQL)
```

---

## 🔍 Estrutura da Implementação

### 📁 Entidade Aluno
```java
@Entity
@Table(name = "aluno")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Aluno {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nome;
   private String email;
   private String cpf;
}
```

### 📁 Repository
```java
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
```

### 📁 Service
```java
@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public void criarAluno(Aluno aluno) {
        alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarAlunoPorId(Long id){
        return alunoRepository.findById(id);
    }

    public void deletarAlunoPorId(Long id){
        alunoRepository.deleteById(id);
    }

    public void atualizarAlunoPorId(Long id, Aluno alunoEditado){
        alunoEditado.setId(id);
        alunoRepository.save(alunoEditado);
    }
}
```

### 📁 Controller
```java
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAluno(@RequestBody Aluno aluno){
        alunoService.criarAluno(aluno);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> listarTodosAlunos(){
        return alunoService.listarTodosAlunos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Aluno> buscarAlunoPorId(@PathVariable Long id){
        return alunoService.buscarAlunoPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAlunoPorId(@PathVariable Long id){
        alunoService.deletarAlunoPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarAlunoPorId(@PathVariable Long id,@RequestBody Aluno alunoEditado){
        alunoService.atualizarAlunoPorId(id, alunoEditado);
    }
}
```

---

### 👨‍🏫 Entidade Professor
```java
@Entity
@Table(name = "professor")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Professor {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nome;
   private String email;
   private String cpf;
}
```

### 📁 Repository
```java
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
```

### 📁 Service
```java
@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    public void criarProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    public List<Professor> listarTodosProfessores() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarProfessorPorId(Long id){
        return professorRepository.findById(id);
    }

    public void deletarProfessorPorId(Long id){
        professorRepository.deleteById(id);
    }

    public void atualizarProfessorPorId(Long id, Professor professorEditado){
        professorEditado.setId(id);
        professorRepository.save(professorEditado);
    }
}
```

### 📁 Controller
```java
@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarProfessor(@RequestBody Professor professor){
        professorService.criarProfessor(professor);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Professor> listarTodosProfessores(){
        return professorService.listarTodosProfessores();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Professor> buscarProfessorPorId(@PathVariable Long id){
        return professorService.buscarProfessorPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProfessorPorId(@PathVariable Long id){
        professorService.deletarProfessorPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProfessorPorId(@PathVariable Long id,@RequestBody Professor professorEditado){
        professorService.atualizarProfessorPorId(id, professorEditado);
    }
}
```

---

## 🔗 Endpoints da API

### 📌 Alunos
- `GET /alunos` → lista todos  
- `POST /alunos` → cria novo  
- `GET /alunos/{id}` → busca por ID  
- `PUT /alunos/{id}` → atualiza por ID  
- `DELETE /alunos/{id}` → remove por ID  

### 📌 Professores
- `GET /professores` → lista todos  
- `POST /professores` → cria novo  
- `GET /professores/{id}` → busca por ID  
- `PUT /professores/{id}` → atualiza por ID  
- `DELETE /professores/{id}` → remove por ID  

---

## 📸 Prints das Requisições (Insomnia)

CRIAR ALUNO
<img width="1266" height="713" alt="image" src="https://github.com/user-attachments/assets/ffba8c65-5c09-46f2-ae0b-0c0c51abaf04" />
LISTAR TODOS ALUNOS
<img width="1266" height="713" alt="image" src="https://github.com/user-attachments/assets/0d9f6067-3da0-4251-a317-c14f91aaa0a2" />
BUSCAR ALUNOS POR ID
<img width="1266" height="713" alt="image" src="https://github.com/user-attachments/assets/49b7eefe-41dc-45e7-8ff8-33dcbac08b6a" />
DELETAR POR ID
<img width="1266" height="713" alt="image" src="https://github.com/user-attachments/assets/9cec3e56-d831-4b96-bbd6-5154fb22c3a4" />
ATUALIZAR POR ID
<img width="1266" height="713" alt="image" src="https://github.com/user-attachments/assets/bb431828-e204-4084-96d0-5aafc180769b" />
CRIAR PROFESSOR
<img width="1266" height="713" alt="image" src="https://github.com/user-attachments/assets/a125cae2-904f-42c5-a807-1d4f6584fd90" />

LISTAR TODOS PROFESSORES
<img width="1266" height="713" alt="image" src="https://github.com/user-attachments/assets/f6e12d83-b202-4fa7-8487-b0734fbca488" />
BUSCAR PROFESSOR POR ID
<img width="1266" height="713" alt="image" src="https://github.com/user-attachments/assets/b3e3d73a-d62c-4941-ab98-a7644feeda68" />
DELETAR POR ID
<img width="1266" height="713" alt="image" src="https://github.com/user-attachments/assets/48af7201-d327-4684-bab5-5691ca1420d5" />
ATUALIZAR POR ID
<img width="1266" height="713" alt="image" src="https://github.com/user-attachments/assets/26938711-9d71-444a-9405-758fe99dcdd7" />

---

## 📸 Prints do Banco (DBeaver)
Aluno
<img width="1366" height="720" alt="image" src="https://github.com/user-attachments/assets/913ea4a3-9dad-4996-8238-55f538526e71" />

Professor
<img width="1366" height="720" alt="image" src="https://github.com/user-attachments/assets/953a842e-4f39-4862-a53f-d23bd5f6e216" />
<img width="662" height="252" alt="image" src="https://github.com/user-attachments/assets/bc38f5ea-50c7-4359-bc7a-07eae806979a" />


---

## 🚀 Como Executar
1. Clone o repositório.  
2. Configure o banco PostgreSQL e ajuste o `application.properties` com suas credenciais.  
3. Rode a aplicação com:
   ```bash
   mvn spring-boot:run
   ```
4. Teste os endpoints via Insomnia ou Postman.  

---

## ✅ Conclusão
Este projeto demonstra como construir uma API REST simples com **Spring Boot**, integrando com **PostgreSQL** e utilizando ferramentas modernas de desenvolvimento. Ele serve como base para evoluir em funcionalidades mais avançadas, como autenticação, relacionamentos entre entidades e documentação automática com Swagger.

---
