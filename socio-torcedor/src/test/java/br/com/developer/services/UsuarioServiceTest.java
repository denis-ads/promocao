package br.com.developer.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.developer.dao.UsuarioDao;
import br.com.developer.model.TimeCoracao;
import br.com.developer.model.Usuario;

public class UsuarioServiceTest extends ServiceTestBase {

    private UsuarioService service;

    private UsuarioDao dao;

    @Before
    public void setUp() {
        service = new UsuarioService();
        dao = Mockito.mock(UsuarioDao.class);
    }

    @Test
    public void testCreate() {
        Usuario usuario = new Usuario();
        usuario.setNome("Joao Da Silva");
        usuario.setEmail("joao@teste.com");
        usuario.setNascimento(new Date());
        TimeCoracao timeCoracao = new TimeCoracao();
        timeCoracao.setId(1L);
        timeCoracao.setNome("Corinthians");
        usuario.setTimeCoracao(timeCoracao);

        try {
            mockDependencyInjection("dao", dao, service);

            service.create(usuario);
            Mockito.verify(dao).create(usuario);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testDeleteById() {
        long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome("Joao Da Silva");
        usuario.setEmail("joao@teste.com");
        usuario.setNascimento(new Date());
        TimeCoracao timeCoracao = new TimeCoracao();
        timeCoracao.setId(1L);
        timeCoracao.setNome("Corinthians");
        usuario.setTimeCoracao(timeCoracao);

        try {
            mockDependencyInjection("dao", dao, service);
            Usuario usuarioRecuperado = new Usuario();
            usuarioRecuperado.setId(id);
            usuarioRecuperado.setNome("Joao Da Silva");
            usuarioRecuperado.setEmail("joao@teste.com");
            usuarioRecuperado.setNascimento(new Date());
            usuarioRecuperado.setTimeCoracao(timeCoracao);
            
            Mockito.when(dao.findById(id)).thenReturn(usuarioRecuperado);

            service.deleteById(usuario.getId());
            Mockito.verify(dao).delete(usuario);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testFindById() {
        long id = 1L;
        Usuario usuario = null;

        try {
            mockDependencyInjection("dao", dao, service);

            Usuario usuarioRecuperado = new Usuario();
            usuarioRecuperado.setId(id);
            
            Mockito.when(dao.findById(id)).thenReturn(usuarioRecuperado);

            usuario = service.findById(id);
            Assert.assertNotNull("Usuario não pode ser null.", usuario);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testUpdate() {
        Usuario usuario = new Usuario();

        try {
            mockDependencyInjection("dao", dao, service);

            Mockito.when(dao.update(usuario)).thenReturn(new Usuario());

            service.update(usuario);
            Mockito.verify(dao).update(usuario);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testListAll() {
        long id = 1L;

        try {
            mockDependencyInjection("dao", dao, service);

            List<Usuario> usuarios = new ArrayList<Usuario>();
            Usuario usuarioRecuperado = new Usuario();
            usuarioRecuperado.setId(id);

            usuarios.add(usuarioRecuperado);

            Integer startPosition=0;
            Integer maxResult=10;
            Mockito.when(dao.listAll(startPosition, maxResult)).thenReturn(usuarios);

            List<Usuario> usuariosConsultados = service.listAll(startPosition, maxResult);
            Assert.assertNotNull("Usuario não pode ser null.", usuariosConsultados);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
