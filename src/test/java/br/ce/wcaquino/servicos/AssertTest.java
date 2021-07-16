package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {
	@Test
	public void test() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		Assert.assertEquals("Erro de comparação", 2, 1, 1);
		Assert.assertEquals(0.51, 0.51, 0.01); // O 0.01 é 0 maximo de diferença entre as comparações
		Assert.assertEquals(Math.PI, 3.14, 0.01);
		
		int i = 5;
		Integer i2 = 5;
		Assert.assertEquals(Integer.valueOf(i), i2);
		Assert.assertEquals(i, i2.intValue());
		
		Assert.assertEquals("bola", "bola");
		Assert.assertNotEquals("bola", "bolas");
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		Assert.assertTrue("bolão".startsWith("bol"));
		
		Usuario u1 = new Usuario("user 1");
		Usuario u2 = new Usuario("user 1");
		
		Assert.assertEquals(u1, u2); // funcionou depois de o metodo equals foi implementado na classe
		
		Usuario u3 = u1;
		
		Assert.assertSame(u1, u3); // se é a mesma instância
		Assert.assertNotSame(u1, u2);
		
		Usuario u4 = null;
		Assert.assertTrue(u4 == null);
		Assert.assertNull(u4);
		Assert.assertNotNull(u1);
		
		
	}
}
