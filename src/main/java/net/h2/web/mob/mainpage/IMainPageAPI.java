package net.h2.web.mob.mainpage;

import java.util.List;

import net.h2.web.core.base.server.api.IBaseAPI;
import net.h2.web.mob.file.page.MainPageFileEntity;
import net.h2.web.mob.mainpage.enums.MainPageType;

public interface IMainPageAPI extends IBaseAPI<MainPageFileEntity, Long> {
	
	List<MainPageFileEntity> getMainPageListByType(MainPageType type);

}
