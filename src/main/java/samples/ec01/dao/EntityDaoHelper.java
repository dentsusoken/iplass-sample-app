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

package samples.ec01.dao;

import java.util.List;

import org.iplass.mtp.ManagerLocator;
import org.iplass.mtp.entity.BinaryReference;
import org.iplass.mtp.entity.Entity;
import org.iplass.mtp.entity.EntityManager;
import org.iplass.mtp.entity.LoadOption;
import org.iplass.mtp.entity.UpdateOption;
import org.iplass.mtp.entity.definition.EntityDefinition;
import org.iplass.mtp.entity.definition.EntityDefinitionManager;
import org.iplass.mtp.entity.query.From;
import org.iplass.mtp.entity.query.Limit;
import org.iplass.mtp.entity.query.Query;
import org.iplass.mtp.entity.query.condition.Condition;

public abstract class EntityDaoHelper {

	private static final EntityManager em = ManagerLocator.getInstance().getManager(EntityManager.class);
	private static final EntityDefinitionManager edm = ManagerLocator.getInstance()
			.getManager(EntityDefinitionManager.class);

	public static <T extends Entity> List<T> searchDistinctEntity(String definitinoName, Condition condition, String... properties) {
		return EntityDaoHelper.searchEntity(true, definitinoName, condition, null, properties);
	}

	public static <T extends Entity> List<T> searchEntity(String definitionName, Condition condition, String... properties) {
		return EntityDaoHelper.searchEntity(false, definitionName, condition, null, properties);
	}

	public static <T extends Entity> List<T> searchDistinctEntity(String definitinoName, Condition condition, Limit limit,
			String... properties) {
		return EntityDaoHelper.searchEntity(true, definitinoName, condition, limit, properties);
	}

	public static <T extends Entity> List<T> searchEntity(String definitionName, Condition condition, Limit limit,
			String... properties) {
		return EntityDaoHelper.searchEntity(false, definitionName, condition, limit, properties);
	}

	@SuppressWarnings("unchecked")
	private static <T extends Entity> List<T> searchEntity(boolean distinct, String definitionName, Condition condition, Limit limit,
			String... properties) {
		Query query = new Query();
		if (definitionName == null) {
			throw new IllegalArgumentException("must specify definition name of entity.");
		} else {
			query.setFrom(new From(definitionName));
		}
		if (condition != null) {
			query.where(condition);
		}
		if (limit != null) {
			query.setLimit(limit);
		}
		if (properties != null && properties.length > 0) {
			if (distinct) {
				query.selectDistinct((Object[]) properties);
			} else {
				query.select((Object[]) properties);
			}
		} else {
			query.selectAll(definitionName, distinct, true);
		}
		//　言語フラグをtrueにする
		query.setLocalized(true);
		return (List<T>) em.searchEntity(query).getList();
	}

	public static List<Object[]> search(Query query) {
		return em.search(query).getList();
	}

	public static int countDistinct(String definitionName, Condition condition) {
		Query query = new Query();
		query.selectDistinct(Entity.OID);
		if (definitionName == null) {
			throw new IllegalArgumentException("must specify definition name of entity.");
		} else {
			query.setFrom(new From(definitionName));
		}
		if (condition != null) {
			query.where(condition);
		}
		//　言語フラグをtrueにする
		query.setLocalized(true);
		return em.count(query);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Entity> T load(String definitionName, String oid) {
		// 言語フラグをtrueにする
		LoadOption option = new LoadOption();
		option.setLocalized(true);
		return (T) em.load(oid, definitionName, option);
	}

	public static String insert(Entity entity) {
		return em.insert(entity);
	}

	public static void update(Entity entity, UpdateOption option) {
		em.update(entity, option);
	}

	public static BinaryReference loadBinaryReference(long lobId) {
		return em.loadBinaryReference(lobId);
	}
	
	public static EntityDefinition getEntityDefinition(String definitionName) {
		return edm.get(definitionName);
	}
}
