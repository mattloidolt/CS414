package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.Payment;

public class PaymentTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPayment() {
		Payment payment = new Payment(4.99, "Credit");
		assertTrue(payment.getAmount() == 4.99);
		assertTrue(payment.getType().equals("Credit"));
	}

}
