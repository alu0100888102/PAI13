package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import modelo.*;
import vista.*;
import java.util.*;
import org.junit.Test;

public class Tests {

	/** Tests de Bola */
	
	@Test
	public final void testBola() {
		Bola b = new Bola();
		assert(b.getX() == 0 && b.getY() == 0 && b.getAngle() == 0 && b.getSpeed() == 0 );
	}

	@Test
	public final void testBolaIntInt() {
		Bola b = new Bola(1,2);
		assert(b.getX() == 1 && b.getY() == 2 && b.getAngle() == 0 && b.getSpeed() == 0 );
	}

	@Test
	public final void testSetX() {
		Bola b = new Bola();
		assert(b.getX() == 0);
		b.setX(10);
		assert(b.getX() == 10);
	}

	@Test
	public final void testSetY() {
		Bola b = new Bola();
		assert(b.getY() == 0);
		b.setY(10);
		assert(b.getY() == 10);
	}

	@Test
	public final void testSetAngle() {
		Bola b = new Bola();
		assert(b.getAngle() == 0);
		b.setAngle(10);
		assert(b.getAngle() == 10);
	}

	@Test
	public final void testSetSpeed() {
		Bola b = new Bola();
		assert(b.getSpeed() == 0);
		b.setSpeed(10);
		assert(b.getSpeed() == 10);
	}

	@Test
	public final void testSetColor() {
		Bola b = new Bola();
		b.setColor(Color.BLUE);
		assert(b.getColor() == Color.BLUE);
	}

	@Test
	public final void testChangeColor() {
		Bola b = new Bola();
		Color first = b.getColor();
		b.changeColor();
		Color second = b.getColor();
		assert(first != second);
	}

	@Test
	public final void testActualizePos() {
		Bola b = new Bola();
		b.setSpeed(10);
		b.setAngle(Math.PI/6);
		b.actualizePos(1);
		assert(b.getX() != 0 && b.getY() != 0);
	}

	@Test
	public final void testIsTouching() {
		Bola b1 = new Bola(0,0);
		Bola b2 = new Bola(10,10);
		Bola b3 = new Bola(60,0);
		Bola b4 = new Bola(60,60);
		assert(b1.isTouching(b2));
		assert(b1.isTouching(b3));
		assert(!b1.isTouching(b4));
	}
	
	/** Test de Cannon */
	
	@Test
	public final void testCannon() {
		Cannon c = new Cannon();
		assert(c.getAngle() == 0 && c.getBola() != null && c.getX() == 0 && c.getY() == 0);
	}

	@Test
	public final void testCannonIntInt() {
		Cannon c = new Cannon(1,2);
		assert(c.getAngle() == 0 && c.getBola() != null && c.getX() == 1 && c.getY() == 2);
	}

	@Test
	public final void testCannonSetAngle() {
		Cannon c = new Cannon();
		assert(c.getAngle() == 0);
		c.setAngle(10);
		assert(c.getAngle() == 10);
	}

	@Test
	public final void testSetBola() {
		Bola b = new Bola(19,10);
		Cannon c = new Cannon();
		Bola oldb = c.getBola();
		c.setBola(b);
		assert(b != oldb);
		assert(c.getBola() == b);
	}

	@Test
	public final void testReload() {
		Cannon c = new Cannon();
		Bola oldb = c.getBola();
		c.reload();
		Bola newb = c.getBola();
		assert(oldb != newb);
	}

	@Test
	public final void testCannonSetX() {
		Cannon c = new Cannon();
		assert(c.getX() == 0);
		c.setX(10);
		assert(c.getX() == 10);
	}

	@Test
	public final void testCannonSetY() {
		Cannon c = new Cannon();
		assert(c.getY() == 0);
		c.setY(10);
		assert(c.getY() == 10);
	}

	@Test
	public final void testFire() {
		Cannon c = new Cannon();
		Bola oldb = c.getBola();
		assert(oldb == c.fire());
		assert(oldb != c.getBola());
	}

	/** Test de Battlefield */
	
	@Test
	public final void testBattlefield() {
		Battlefield b = new Battlefield();
		assert(b.getBolas() != null && b.getProyectil() != null);
	}

	@Test
	public final void testGenerateLevel() {
		Battlefield b = new Battlefield();
		ArrayList<Bola> old = b.getBolas();
		b.generateLevel();
		ArrayList<Bola> recent = b.getBolas();
		assert(old != recent);
		for(int i =0; i < old.size(); i++)
			assert(old.get(i).getX() == recent.get(i).getX() && old.get(i).getY() == recent.get(i).getY());
	}
	
	@Test
	public final void testSetBolas() {
		Battlefield b = new Battlefield();
		ArrayList<Bola> old = b.getBolas();
		ArrayList<Bola> next = new ArrayList<Bola>();
		b.setBolas(next);
		assert(next != old && next == b.getBolas());
		
	}

	@Test
	public final void testSetProyectil() {
		Battlefield b = new Battlefield();
		Bola p = b.getProyectil();
		Bola n = new Bola();
		b.setProyectil(n);
		assert(p != n && b.getProyectil() == n);
	}

	@Test
	public final void testSetCannon() {
		Battlefield b = new Battlefield();
		Cannon n = new Cannon();
		Cannon o = b.getCannon();
		b.setCannon(n);
		assert(o != n && b.getCannon() == n);
	}

	@Test
	public final void testVirtualX() {
		Battlefield b = new Battlefield();
		b.setSize(1200, 600);
		assert(b.virtualX(0) == 0);
		assert(b.virtualX(100) == 100);
		b.setSize(2400, 1200);
		assert(b.virtualX(0) == 0);
		assert(b.virtualX(100) == 200);
	}

	@Test
	public final void testVirtualY() {
		Battlefield b = new Battlefield();
		b.setSize(1200, 600);
		assert(b.virtualY(0) == 600);
		assert(b.virtualY(100) == 500);
		b.setSize(2400, 1200);
		assert(b.virtualY(0) == 1200);
		assert(b.virtualY(100) == 1000);
	}
}
