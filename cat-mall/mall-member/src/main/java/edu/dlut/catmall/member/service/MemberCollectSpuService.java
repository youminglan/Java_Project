package edu.dlut.catmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.dlut.common.utils.PageUtils;
import edu.dlut.catmall.member.entity.MemberCollectSpuEntity;

import java.util.Map;

/**
 * 会员收藏的商品
 *
 * @author raymond
 * @email qq740567396@gmail.com
 * @date 2020-04-30 09:47:50
 */
public interface MemberCollectSpuService extends IService<MemberCollectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

