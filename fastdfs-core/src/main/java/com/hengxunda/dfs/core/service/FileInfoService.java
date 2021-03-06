package com.hengxunda.dfs.core.service;

import com.hengxunda.dfs.base.cache.CacheService;
import com.hengxunda.dfs.core.entity.AppInfoEntity;
import com.hengxunda.dfs.core.entity.FileInfoEntity;
import com.hengxunda.dfs.core.mapper.FileInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 文件信息服务类
 */
@Service
public class FileInfoService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    /**
     * 添加文件信息
     *
     * @return
     */
    public int addFileInfo(String appKey, int accessType, String fileName, long fileLength) {
        AppInfoEntity app = CacheService.APP_INFO_CACHE.get(appKey);
        FileInfoEntity fileInfo = new FileInfoEntity();
        fileInfo.setName(fileName);
        fileInfo.setBytes(fileLength);
        fileInfo.setGroupName(app.getGroupName());
        fileInfo.setAccessType(accessType);
        fileInfo.setBelongsApp(appKey);
        fileInfo.setStatus(FileInfoEntity.FILE_STATUS_CREATED);
        Date now = new Date();
        fileInfo.setCreateDate(now);
        fileInfo.setUpdateDate(now);
        fileInfoMapper.addFileInfo(fileInfo);
        return fileInfo.getId();
    }

    /**
     * 按fileInfoId更新fileInfo必须设置id字段
     *
     * @param fileInfo
     */
    public void updateFileInfoById(FileInfoEntity fileInfo) {
        fileInfoMapper.updateFileInfoById(fileInfo);
    }

    /**
     * 按fileId更新fileInfo必须设置fileId字段
     *
     * @param fileInfo
     */
    public void updateFileInfoByFileId(FileInfoEntity fileInfo) {
        fileInfoMapper.updateFileInfoByFileId(fileInfo);
    }

    /**
     * 获取文件长度
     *
     * @param fileId
     * @return
     */
    public long getFileLengthByFileId(String fileId) {
        return fileInfoMapper.getFileLengthByFileId(fileId);
    }

    /**
     * 获取文件名
     *
     * @param fileId
     * @return
     */
    public String getFileNameByFileId(String fileId) {
        return fileInfoMapper.getFileNameByFileId(fileId);
    }

}
