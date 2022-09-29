package br.com.almanak.almanakApi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.almanak.almanakApi.Interface.CategoriaDTO;
import br.com.almanak.almanakApi.Interface.FilterDTO;
import br.com.almanak.almanakApi.Interface.JogoDTO;
import br.com.almanak.almanakApi.Interface.JogoItemDTO;
import br.com.almanak.almanakApi.Interface.RegraDTO;
import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Categoria;
import br.com.almanak.almanakApi.model.Item;
import br.com.almanak.almanakApi.model.Jogo;
import br.com.almanak.almanakApi.model.JogoCategoriaRel;
import br.com.almanak.almanakApi.model.JogoItemRel;
import br.com.almanak.almanakApi.model.Regra;
import br.com.almanak.almanakApi.model.Usuario;
import br.com.almanak.almanakApi.service.CategoriaService;
import br.com.almanak.almanakApi.service.ItemService;
import br.com.almanak.almanakApi.service.JogoService;
import br.com.almanak.almanakApi.service.UsuarioService;
import br.com.almanak.almanakApi.utilities.Calculadora;


@Controller
@RequestMapping("/api/jogo")
public class JogoController {

    @Autowired
    private JogoService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ItemService itemService;
 
    @GetMapping
    public Page<Jogo> index(Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<JogoDTO> show(@PathVariable Integer id){
        Optional<Jogo> opt = service.findById(id);

        if(!opt.isEmpty()){
            
            Optional<JogoDTO> dto = Optional.of(new JogoDTO().convert(opt.get()));
            Optional<Double> optScore = service.findScore(opt.get().getId());
            if(!optScore.isEmpty()){
                Double score = Calculadora.ajusteNota(optScore.get());
                dto.get().setScore(score);
            }
            
            return ResponseEntity.of(dto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("valido/{flag}")
    public ResponseEntity<List<JogoDTO>> listByValid(@PathVariable() EN_Booleano flag){
        Optional<List<Jogo>> opts = service.listByValid(flag);

        if(!opts.isEmpty()){
            List<Jogo> jogos = opts.get();
            List<JogoDTO> dtoList = new ArrayList<JogoDTO>();

            for(Jogo j : jogos){
                JogoDTO dto = new JogoDTO().convert(j);

                Optional<Double> optScore = service.findScore(j.getId());
                if(!optScore.isEmpty()){
                    Double score = Calculadora.ajusteNota(optScore.get());
                    dto.setScore(score);
                }

                dto.setRegras(null);
                dto.setItens(null);

                dtoList.add(dto);

            }
    
            return ResponseEntity.ok().body(dtoList);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
    }
        
    @GetMapping("nome/{nome}")
    public ResponseEntity<JogoDTO> findByName(@PathVariable String nome){
        Optional<Jogo> opt = service.findByName(nome);

        if(!opt.isEmpty()){
            
            Optional<JogoDTO> dto = Optional.of(new JogoDTO().convert(opt.get()));
            Optional<Double> optScore = service.findScore(opt.get().getId());
            if(!optScore.isEmpty()){
                Double score = Calculadora.ajusteNota(optScore.get());
                dto.get().setScore(score);
            }

            return ResponseEntity.of(dto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("categoria/{categoria}")
    public ResponseEntity<List<JogoDTO>> findByCategoria(@PathVariable String categoria){
        Optional<List<Jogo>> opt = service.listByCategoria(categoria);
        
        if(!opt.isEmpty()){
            List<Jogo> optList = opt.get();
            List<JogoDTO> jogosDTO = new ArrayList<JogoDTO>();

            for(Jogo j : optList){
                JogoDTO jogoDTO = new JogoDTO().convert(j);
                Optional<Double> optScore = service.findScore(j.getId());

                if(!optScore.isEmpty()){
                    Double score = Calculadora.ajusteNota(optScore.get());
                    jogoDTO.setScore(score);
                }
                                
                jogoDTO.setRegras(null);
                jogoDTO.setItens(null);

                jogosDTO.add(jogoDTO);

            }

            return ResponseEntity.ok(jogosDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("categoria")
    public ResponseEntity<List<JogoDTO>> listByListCategoria(@RequestBody List<String> categorias){
        Optional<List<Jogo>> opt = service.listByListCategoria(categorias);
        
        if(!opt.isEmpty()){
            List<Jogo> optList = opt.get();
            List<JogoDTO> jogosDTO = new ArrayList<JogoDTO>();

            for(Jogo j : optList){
                JogoDTO jogoDTO = new JogoDTO().convert(j);
                Optional<Double> optScore = service.findScore(j.getId());

                if(!optScore.isEmpty()){
                    Double score = Calculadora.ajusteNota(optScore.get());
                    jogoDTO.setScore(score);
                }
                                
                jogoDTO.setRegras(null);
                jogoDTO.setItens(null);

                jogosDTO.add(jogoDTO);

            }

            return ResponseEntity.ok(jogosDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("recomendados/{id}")
    public ResponseEntity<List<JogoDTO>> listRecommended(@PathVariable Integer id){
        Optional<List<Jogo>> opt = service.listRecommended(id);
        
        if(!opt.isEmpty()){
            List<Jogo> optList = opt.get();
            List<JogoDTO> jogosDTO = new ArrayList<JogoDTO>();

            for(Jogo j : optList){
                JogoDTO jogoDTO = new JogoDTO().convert(j);
                Optional<Double> optScore = service.findScore(j.getId());

                if(!optScore.isEmpty()){
                    Double score = Calculadora.ajusteNota(optScore.get());
                    jogoDTO.setScore(score);
                }
                                
                jogoDTO.setRegras(null);
                jogoDTO.setItens(null);

                jogosDTO.add(jogoDTO);

            }

            return ResponseEntity.ok(jogosDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("filtro")
    public ResponseEntity<List<JogoDTO>> listByListFilters(@RequestBody FilterDTO filtroDTO){
        Optional<List<Jogo>> opt = service.listByListFilters(filtroDTO);
        
        if(!opt.isEmpty()){
            List<Jogo> optList = opt.get();
            List<JogoDTO> jogosDTO = new ArrayList<JogoDTO>();

            for(Jogo j : optList){
                JogoDTO jogoDTO = new JogoDTO().convert(j);
                Optional<Double> optScore = service.findScore(j.getId());

                if(!optScore.isEmpty()){
                    Double score = Calculadora.ajusteNota(optScore.get());
                    jogoDTO.setScore(score);
                }
                                
                jogoDTO.setRegras(null);
                jogoDTO.setItens(null);

                jogosDTO.add(jogoDTO);

            }

            return ResponseEntity.ok(jogosDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
    
    @PostMapping
    public ResponseEntity<JogoDTO> create(@RequestBody @Valid JogoDTO jogoDTO){

        Optional<Jogo> opt = service.findByName(jogoDTO.getName());
        Optional<Usuario> optusuario = usuarioService.getById(jogoDTO.getCriador());

        if(opt.isEmpty() && !optusuario.isEmpty()){

            try{
                Usuario usuario = optusuario.get();

                Jogo jogo  = new Jogo();
                jogo.setName(jogoDTO.getName());
                jogo.setMinJogadores(jogoDTO.getMinJogadores());
                jogo.setMaxJogadores(jogoDTO.getMaxJogadores());
                jogo.setParaAdultos(jogoDTO.getParaAdultos());
                jogo.setValido(EN_Booleano.sim);
                jogo.setElite(EN_Booleano.nao);
                jogo.addUsuario(usuario);

                for(CategoriaDTO c : jogoDTO.getCategorias()){
                    Categoria categoria = categoriaService.findById(c.getId()).get();
                    JogoCategoriaRel rel = new JogoCategoriaRel();
                    rel.addCategoria(categoria);
                    rel.addJogo(jogo);
                    rel.setDtRegistro();
                }

                for(RegraDTO r : jogoDTO.getRegras()){
                    Regra regra = new Regra();
                    regra.setName(r.getNome());
                    regra.setPosicao(r.getPosicao());
                    regra.setDesc(r.getDesc());
                    regra.setOpcional(r.getOpcional());                    
                    regra.addJogo(jogo);
                    regra.setDtRegistro();
                }

                for(JogoItemDTO ji : jogoDTO.getItens()){
                    JogoItemRel rel = new JogoItemRel(ji.getQtd(), ji.getNecessario());
                    Item item = itemService.findById(ji.getItem().getId()).get();
                    rel.addItem(item);
                    rel.addJogo(jogo);
                    rel.setDtRegistro();
                }
    
                service.save(jogo);
                JogoDTO dto = new JogoDTO().convert(jogo);
                
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(dto);

            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("nome/{nome}")
    public ResponseEntity<Jogo> destroyByName(@PathVariable String nome){
        var jogoResponse = service.remove(nome);
        if(jogoResponse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            
            var jogo = jogoResponse.get();
            if(jogo.getId() == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                
            return ResponseEntity.ok(jogo);

        }
        
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Jogo> destroy(@PathVariable Integer id){
        var jogoResponse = service.remove(id);
        if(jogoResponse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            
            var jogo = jogoResponse.get();
            if(jogo.getId() == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                
            return ResponseEntity.ok(jogo);

        }
        
    }
    
}
