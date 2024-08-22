/*
 * Copyright (C) 2018 DENTSU SOKEN INC. All Rights Reserved.
 * 
 * Unless you have purchased a commercial license,
 * the following license terms apply:
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package samples.ec01.test.command;

import static org.junit.Assert.*;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.entity.BinaryReference;
import org.iplass.mtp.entity.EntityManager;
import org.iplass.mtp.entity.SearchResult;
import org.iplass.mtp.entity.query.Query;
import org.iplass.mtp.entity.query.condition.predicate.Equals;
import org.iplass.mtp.test.MTPJUnitTestExtension;
import org.iplass.mtp.test.MTPTest;
import org.iplass.mtp.test.NoAuthUser;
import org.iplass.mtp.test.TestRequestContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import samples.ec01.entity.Product;

@ExtendWith(MTPJUnitTestExtension.class)
class TopCommandTest {

	static final String COMMAND_NAME = "samples/ec01/TopCommand";

	/**
	 * TopCommandでオススメ商品を検索する際のMock関数を設定する。
	 */
	@BeforeAll
	static void setUpBeforeClass() {
		@SuppressWarnings("serial")
		EntityManager mock = (EntityManager) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
				new Class<?>[] { EntityManager.class }, (proxy, method, args) -> {
					String methodName = method.getName();
					if (methodName.equals("searchEntity")) {
						if (args.length == 1 && args[0] instanceof Query) {
							Query query = (Query) args[0];
							String entityName = query.getFrom().getEntityName();
							Equals cond = (Equals) query.getWhere().getCondition();
							
							if (Product.DEFINITION_NAME.equals(entityName)
									&& cond.equals(new Equals(Product.RECOMMEND, true))) {
								return new SearchResult<Product>(2, new ArrayList<Product>() {
									{
										Product product01 = new Product();
										product01.setOid("TEST_OID_01");
										product01.setName("TEST_NAME_01");
										product01.setPrice(10000L);
										product01.setProductImg(
												new BinaryReference(1L, "TEST_IMG_01", "image/jpeg", 100));
										add(product01);

										Product product02 = new Product();
										product02.setOid("TEST_OID_02");
										product02.setName("TEST_NAME_02");
										product02.setPrice(20000L);
										product02.setProductImg(
												new BinaryReference(2L, "TEST_IMG_02", "image/png", 200));
										add(product02);
									}
								});
							}
						}
					}
					return null;
				});
		MTPTest.setManagerMock(EntityManager.class, mock);
	}

	@AfterAll
	static void tearDownAfterClass() {
		MTPTest.resetManagerMock();
	}

	@Test
	@NoAuthUser
	void testExecuteCommand() {
		TestRequestContext req = new TestRequestContext();
		String status = MTPTest.invokeCommand(COMMAND_NAME, req);
		assertEquals(Constants.CMD_EXEC_SUCCESS, status);

		@SuppressWarnings("unchecked")
		List<Product> productList = (List<Product>) req.getAttribute("productList");
		assertEquals(2, productList.size());

		Product product01 = productList.get(0);
		assertEquals("TEST_OID_01", product01.getOid());
		assertEquals("TEST_NAME_01", product01.getName());
		assertEquals(Long.valueOf(10000), product01.getPrice());
		BinaryReference br01 = product01.getProductImg();
		assertEquals(1L, br01.getLobId());
		assertEquals("TEST_IMG_01", br01.getName());
		assertEquals("image/jpeg", br01.getType());
		assertEquals(100, br01.getSize());

		Product product02 = productList.get(1);
		assertEquals("TEST_OID_02", product02.getOid());
		assertEquals("TEST_NAME_02", product02.getName());
		assertEquals(Long.valueOf(20000), product02.getPrice());
		BinaryReference br02 = product02.getProductImg();
		assertEquals(2L, br02.getLobId());
		assertEquals("TEST_IMG_02", br02.getName());
		assertEquals("image/png", br02.getType());
		assertEquals(200, br02.getSize());
	}

}
