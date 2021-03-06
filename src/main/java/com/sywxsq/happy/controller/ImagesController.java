package com.sywxsq.happy.controller;

import com.sywxsq.happy.pojo.Images;
import com.sywxsq.happy.pojo.PageResult;
import com.sywxsq.happy.pojo.SywxsqException;
import com.sywxsq.happy.pojo.SywxsqResult;
import com.sywxsq.happy.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 图片
 * @author luokangtao
 * @create 2019-02-14 11:00
 * @RestController是Controller+ResponseBody结合
 */
@RestController
@RequestMapping("/ImagesController")
public class ImagesController {

    @Autowired
    private ImagesService imagesService;

    //结果集
    private SywxsqResult sywxsqResult =null;

    /**
     * 新增图片(改良后调用这个方法: com.sywxsq.happy.controller.UploadController.uploadImages)
     * 这个方法暂时不用
     * @param images
     * @return
     */
    @RequestMapping("/addImages")
    public SywxsqResult addImages(@RequestBody Images images){

        if(images.getImgUrl()==null){
            throw new SywxsqException("请上传图片,图片不能为空!");
        }
        boolean b=imagesService.addImages(images);
        if(b){
            sywxsqResult = new SywxsqResult(true,"新增图片成功");
        }else {
            sywxsqResult = new SywxsqResult(false,"新增图片失败");
        }
        return sywxsqResult;
    }

    /**
     * 分页查询所有的相片
     * @return
     */
    @RequestMapping("/findAllImages")
    public SywxsqResult findAllImages(Integer pageNumber,Integer pageSize){
        PageResult images = imagesService.findAllImages(pageNumber, pageSize);
        sywxsqResult =new SywxsqResult(true,"查询成功");
        sywxsqResult.setPageResult(images);
        return sywxsqResult;
    }

    /**
     * 删除图片
     * @param id  根据id删除数据库信息
     * @param imgUrl 根据地址删除linux里面的图片
     * @return
     */
    @RequestMapping("/deleteImages")
    public SywxsqResult deleteImages(Integer id,String imgUrl){
        if(id==null){
            throw new SywxsqException("id值不能为空");
        }
        if(imgUrl==null||imgUrl.isEmpty()){
            throw  new SywxsqException("imgUrl不能为空");
        }
        //调用方法
        boolean b=imagesService.deleteImages(id,imgUrl);
        if(b){
            sywxsqResult = new SywxsqResult(true,"删除图片成功");
        }else {
            sywxsqResult = new SywxsqResult(false,"删除图片失败");
        }
        return sywxsqResult;
    }

}
