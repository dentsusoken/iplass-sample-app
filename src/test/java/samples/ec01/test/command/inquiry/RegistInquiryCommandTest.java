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

package samples.ec01.test.command.inquiry;

import static org.junit.Assert.*;

import java.util.List;

import org.iplass.mtp.ManagerLocator;
import org.iplass.mtp.command.beanmapper.MappingException;
import org.iplass.mtp.command.beanmapper.MappingResult;
import org.iplass.mtp.entity.EntityManager;
import org.iplass.mtp.entity.SelectValue;
import org.iplass.mtp.entity.query.Query;
import org.iplass.mtp.test.MTPJUnitTestRule;
import org.iplass.mtp.test.MTPTest;
import org.iplass.mtp.test.NoAuthUser;
import org.iplass.mtp.test.Rollback;
import org.iplass.mtp.test.TestRequestContext;
import org.junit.Rule;
import org.junit.Test;

import samples.ec01.entity.Inquiry;
import samples.ec01.enums.InquiryStatus;


public class RegistInquiryCommandTest {

	static final String COMMAND_NAME = "samples/ec01/inquiry/RegistInquiryCommand";

	@Rule
	public MTPJUnitTestRule rule = new MTPJUnitTestRule();

	/**
	 * <pre>
	 * お問合せのテストデータをRDBに登録します。
	 * テストケースの処理が完了したら、登録したテストデータをロールバックします。</pre>
	 */
	@Test
	@NoAuthUser
	@Rollback
	public void testRegist() {
		TestRequestContext req = new TestRequestContext();
		req.setParam("mail", "testXyz123@test.co.jp");
		req.setParam("content", "テスト問い合わせ内容");
		req.setParam("familyName", "test");
		req.setParam("firstName", "ichirou");
		req.setParam("familyNameKana", "テスト");
		req.setParam("firstNameKana", "イチロウ");

		MTPTest.transaction(() -> {
			String status = MTPTest.invokeCommand(COMMAND_NAME, req);
			assertEquals("SUCCESS", status);
			
			Query query = new Query(
					"select mail, content, familyName, firstName, familyNameKana, firstNameKana, inquiryStatus from samples.ec01.Inquiry where mail = 'testXyz123@test.co.jp'");
			EntityManager em = ManagerLocator.getInstance().getManager(EntityManager.class);
			Inquiry inquiry = (Inquiry) em.searchEntity(query).getFirst();
			assertEquals("testXyz123@test.co.jp", inquiry.getMail());
			assertEquals("テスト問い合わせ内容", inquiry.getContent());
			assertEquals("test", inquiry.getFamilyName());
			assertEquals("ichirou", inquiry.getFirstName());
			assertEquals("テスト", inquiry.getFamilyNameKana());
			assertEquals("イチロウ", inquiry.getFirstNameKana());
			assertEquals(new SelectValue(InquiryStatus.NOT_DEAL.getValue()), inquiry.getInquiryStatus());
		});
	}

	@Test(expected = MappingException.class)
	@NoAuthUser
	public void testWithBlankValue() {
		TestRequestContext req = new TestRequestContext();
		req.setParam("mail", "");
		req.setParam("content", "");
		req.setParam("familyName", "");
		req.setParam("firstName", "");
		req.setParam("familyNameKana", "");
		req.setParam("firstNameKana", "");

		try {
			MTPTest.invokeCommand(COMMAND_NAME, req);
		} catch (MappingException e) {
			MappingResult res = e.getResult();
			assertEquals(6, res.getErrors().size());

			List<String> errMsg = res.getError("mail").getErrorMessages();
			assertEquals(1, errMsg.size());
			assertEquals("値を入力してください。", errMsg.get(0));

			errMsg = res.getError("content").getErrorMessages();
			assertEquals(1, errMsg.size());
			assertEquals("値を入力してください。", errMsg.get(0));

			errMsg = res.getError("familyName").getErrorMessages();
			assertEquals(1, errMsg.size());
			assertEquals("値を入力してください。", errMsg.get(0));

			errMsg = res.getError("firstName").getErrorMessages();
			assertEquals(1, errMsg.size());
			assertEquals("値を入力してください。", errMsg.get(0));

			errMsg = res.getError("familyNameKana").getErrorMessages();
			assertEquals(1, errMsg.size());
			assertEquals("値を入力してください。", errMsg.get(0));

			errMsg = res.getError("firstNameKana").getErrorMessages();
			assertEquals(1, errMsg.size());
			assertEquals("値を入力してください。", errMsg.get(0));

			throw e;
		}

		assertFalse(true);
	}

	@Test(expected = MappingException.class)
	@NoAuthUser
	public void testWithInvalidValue() {
		TestRequestContext req = new TestRequestContext();
		req.setParam("mail", "test.co.jp");
		req.setParam("content", "テスト問い合わせ内容");
		req.setParam("familyName", "test");
		req.setParam("firstName", "ichirou");
		req.setParam("familyNameKana", "test");
		req.setParam("firstNameKana", "ichirou");

		try {
			MTPTest.invokeCommand(COMMAND_NAME, req);
		} catch (MappingException e) {
			MappingResult res = e.getResult();
			assertEquals(3, res.getErrors().size());

			List<String> errMsg = res.getError("mail").getErrorMessages();
			assertEquals(1, errMsg.size());
			assertEquals("メールアドレスを入力してください。", errMsg.get(0));

			errMsg = res.getError("familyNameKana").getErrorMessages();
			assertEquals(1, errMsg.size());
			assertEquals("全角カタカナを入力してください。", errMsg.get(0));

			errMsg = res.getError("firstNameKana").getErrorMessages();
			assertEquals(1, errMsg.size());
			assertEquals("全角カタカナを入力してください。", errMsg.get(0));

			throw e;
		}

		assertFalse(true);
	}
}
