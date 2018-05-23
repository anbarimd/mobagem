package net.h2.web.mob.login;

import net.h2.web.core.base.server.dao.BaseDaoImpl;

import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl extends BaseDaoImpl<LoginEntity, Long>
		implements ILoginDAO {

}
