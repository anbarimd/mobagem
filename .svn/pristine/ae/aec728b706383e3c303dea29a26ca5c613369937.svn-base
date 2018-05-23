package net.h2.web.mob.mainpage;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.h2.web.core.base.shared.service.BaseServiceImpl;
import net.h2.web.core.utils.Base64EncodeDecode;
import net.h2.web.mob.file.page.MainPageFileDTO;
import net.h2.web.mob.file.page.MainPageFileEntity;
import net.h2.web.mob.mainpage.enums.MainPageType;

import org.springframework.stereotype.Service;

@Service
public class MainPageServiceImpl extends BaseServiceImpl<MainPageFileDTO, MainPageFileEntity, Long, IMainPageAPI>
		implements IMainPageService {

	@Override
	public List<MainPageFileDTO> getMainPageListByType(MainPageType type) {
		List<MainPageFileEntity> list = api.getMainPageListByType(type);
		List<MainPageFileDTO> dtos = new ArrayList<MainPageFileDTO>();

		if (list != null && !list.isEmpty()) {

			for (MainPageFileEntity entity : list) {
				MainPageFileDTO dto = convertEntityToDTO(entity);
				dtos.add(dto);
			}

		}
		return dtos;
	}

	@Override
	protected MainPageFileDTO convertEntityToDTO(MainPageFileEntity entity) {
		MainPageFileDTO dto = super.convertEntityToDTO(entity);
		try {
			dto.setPhotoStr(
					"data:image/png;base64," + Base64EncodeDecode.convertByteArrayToBase64String(entity.getPhoto()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return dto;
	}

}
