package cn.xawl.by.service;

import cn.xawl.by.dao.TeamDao;
import cn.xawl.by.pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamDao teamDao;

    public List<Team> findAllTeam() {
        List<Team> list = teamDao.findAllTeam();
        return list;
    }

    public Team addTeam(Team team) {
        return teamDao.addTeam(team);
    }

    public int updateTeam(Team team) {
        int i = teamDao.updateTeam(team);
        return i;
    }

    public void delTeam(Team team) {
        teamDao.delTeam(team);
    }

    public Team findByAccount(String account) {
        return teamDao.findByAccount(account);
    }

    public Team findByTid(String tid) {
        return teamDao.findByTid(tid);
    }
}
