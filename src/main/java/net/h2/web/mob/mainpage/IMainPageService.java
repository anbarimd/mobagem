package net.h2.web.mob.mainpage;

import java.util.List;

import net.h2.web.core.base.shared.service.IBaseService;
import net.h2.web.mob.file.page.MainPageFileDTO;
import net.h2.web.mob.mainpage.enums.MainPageType;

public interface IMainPageService extends IBaseService<MainPageFileDTO, Long> {

	List<MainPageFileDTO> getMainPageListByType(MainPageType type);

}
