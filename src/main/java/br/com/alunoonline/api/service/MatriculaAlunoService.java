package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.AtualizarNotasRequest;
import br.com.alunoonline.api.dtos.DisciplinasAlunoResponse;
import br.com.alunoonline.api.dtos.HistoricoAlunoResponse;
import br.com.alunoonline.api.enums.MatriculaAlunoStatusEnum;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaAlunoService {

    public static final double MEDIA_PARA_APROVACAO = 7.0;

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    public void criarMatricula(MatriculaAluno matriculaAluno) {
        matriculaAluno.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void trancarMatricula(Long matriculaAlunoId){
        MatriculaAluno matriculaAluno =
                matriculaAlunoRepository.findById(matriculaAlunoId)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "Matricula do Aluno não encontrada"));

        if (!MatriculaAlunoStatusEnum.MATRICULADO.equals(matriculaAluno.getStatus())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Só é possível trancar uma matrícula com o status MATRICULADO");
        }
        matriculaAluno.setStatus(MatriculaAlunoStatusEnum.TRANCADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void atualizarNotas(Long matriculaAlunoId, AtualizarNotasRequest atualizarNotasRequest){

        MatriculaAluno matriculaAluno =
                matriculaAlunoRepository.findById(matriculaAlunoId)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "Matricula do Aluno não encontrada"));


        if(atualizarNotasRequest.getNota1() != null){
            matriculaAluno.setNota1(atualizarNotasRequest.getNota1());
        }
        if(atualizarNotasRequest.getNota2() != null){
            matriculaAluno.setNota2(atualizarNotasRequest.getNota2());
        }

        calcularMedia(matriculaAluno);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    private Double calcularMedia (MatriculaAluno matriculaAluno){
        Double nota1 = matriculaAluno.getNota1();
        Double nota2 = matriculaAluno.getNota2();

        if(nota1 != null && nota2 != null){
           Double media = (nota1 + nota2) / 2;
           matriculaAluno.setStatus(media >= MEDIA_PARA_APROVACAO ? MatriculaAlunoStatusEnum.APROVADO : MatriculaAlunoStatusEnum.REPROVADO);
           return media;
        }
        return null;
    }


    public HistoricoAlunoResponse emitirHistorico(Long alunoId){

        List<MatriculaAluno> matriculasAluno = matriculaAlunoRepository.findByAlunoId(alunoId);

        if(matriculasAluno.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Esse aluno não possui matriculas");
        }

        HistoricoAlunoResponse historicoAlunoResponse = new HistoricoAlunoResponse();
        historicoAlunoResponse.setNomeAluno(matriculasAluno.get(0).getAluno().getNome());
        historicoAlunoResponse.setCpfAluno(matriculasAluno.get(0).getAluno().getCpf());
        historicoAlunoResponse.setEmailAluno(matriculasAluno.get(0).getAluno().getEmail());

        List<DisciplinasAlunoResponse> disciplinasList = new ArrayList<>();

        for(MatriculaAluno matriculaAluno : matriculasAluno){
            DisciplinasAlunoResponse disciplinasAlunoResponse = new DisciplinasAlunoResponse();
            disciplinasAlunoResponse.setNomeDisciplina(matriculaAluno.getDisciplina().getNome());
            disciplinasAlunoResponse.setNomeProfessor(matriculaAluno.getDisciplina().getProfessor().getNome());
            disciplinasAlunoResponse.setNota1(matriculaAluno.getNota1());
            disciplinasAlunoResponse.setNota2(matriculaAluno.getNota2());

            if (matriculaAluno.getNota1() != null && matriculaAluno.getNota2() != null) {
                disciplinasAlunoResponse.setMedia(calcularMedia(matriculaAluno));
            } else {
                disciplinasAlunoResponse.setMedia(null);
            }

            disciplinasAlunoResponse.setStatus(matriculaAluno.getStatus());

            disciplinasList.add(disciplinasAlunoResponse);
        }

        historicoAlunoResponse.setDisciplinasAlunoResponses(disciplinasList);

        return historicoAlunoResponse;
    }
}
