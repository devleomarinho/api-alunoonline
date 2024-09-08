<h2>Aluno Online API</h2>
Repositório do projeto "Aluno Online", desenvolvido na disciplina "Tecnologias para Backend", do professor Kelson Victor

<h3>Exercício 1: </h3> Neste exercício, foi criado o endpoint para uma requisição do tipo "Post" para criação de um aluno e persistir os dados no banco de dados da aplicação. Para isso, foi necessário primeiro fazer a injeção da dependência da interface "AlunoRepository", usando a anotação @Autowired,  dentro da classe "AlunoService" e escrever o método "criarAluno", que por sua vez invoca o método "save", já herdado pela interface atraves do JpaRepository. Este método recebe como payload a própria entidade Aluno:
</br>
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public void criarAluno(Aluno aluno){
        alunoRepository.save(aluno);
    }

}
</br>
Em seguida, na classe de controle "AlunoController", foi injetada a dependência da classe "AlunoService" e em seguida foi mapeada a requisição post para receber em seu corpo os dados da entidade "Aluno" e então chamar o método "criarAluno" da classe de service, retornando o status Http "201 - Created":
</br>
public class AlunoController {
    @Autowired
    AlunoService alunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAluno(@RequestBody Aluno aluno){
        alunoService.criarAluno(aluno);
    }

}

Abaixo, seguem os prints desta requisição funcionando no Insomnia e dos dados salvos no banco de dados postgresql:

![criarAluno_teste_insomina](https://github.com/user-attachments/assets/b697f0ac-1e8d-4f49-a1ea-256bcec1f766)

![criarAluno_teste_dbeaver](https://github.com/user-attachments/assets/03c01583-05b1-467f-9244-d80d1f6c6b7a)


