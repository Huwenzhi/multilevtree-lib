package com.hu_sir.multilevtree.listerners;

import com.hu_sir.multilevtree.bean.Node;
/**     
  * Copyright (C), 2015-2019/6/5
  * @ProjectName:    OnTreeNodeClickListener.java
  * @Package:        com.hu_sir.multilevtree.listerners
  * @ClassName:      OnTreeNodeClickListener
  * @Description:     TODO
  * @Author:          Hu_Sir
  * @CreateDate:     2019/6/5--16:38
  * @UpdateUser:     ?
  * @UpdateDate:     2019/6/5--16:38
  * @UpdateRemark:   todo
  * 
 */
public interface OnTreeNodeClickListener {
    void onClick(Node node, int position);
}
