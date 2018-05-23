package net.h2.web.mob.mainpage;

import java.util.List;

import net.h2.web.core.base.server.api.BaseApiImpl;
import net.h2.web.mob.file.page.MainPageFileEntity;
import net.h2.web.mob.mainpage.enums.MainPageType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MainPageAPI extends BaseApiImpl<MainPageFileEntity, Long, IMainPageDAO> implements IMainPageAPI {

	@Override
	@Transactional(readOnly = true)
	public List<MainPageFileEntity> getMainPageListByType(MainPageType type) {
		return dao.getMainPageListByType(type);
	}

}
