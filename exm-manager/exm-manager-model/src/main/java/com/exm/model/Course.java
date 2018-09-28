package com.exm.model;

public class Course {
    public Integer getMark()
	{
		return mark;
	}

	public void setMark(Integer mark)
	{
		this.mark = mark;
	}

	@Override
	public String toString()
	{
		return "Course [id=" + id + ", courseName=" + courseName + ", teacherId=" + teacherId + ", courseTime="
				+ courseTime + ", classRoom=" + classRoom + ", courseWeek=" + courseWeek + ", courseType=" + courseType
				+ ", collegeId=" + collegeId + ", score=" + score + "]";
	}

	private Long id;

    private String courseName;

    private Integer teacherId;

    private String courseTime;

    private String classRoom;

    private Integer courseWeek;

    private String courseType;

    private Integer collegeId;

    private Integer score;

    
    //后增加
    private Integer mark;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime == null ? null : courseTime.trim();
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom == null ? null : classRoom.trim();
    }

    public Integer getCourseWeek() {
        return courseWeek;
    }

    public void setCourseWeek(Integer courseWeek) {
        this.courseWeek = courseWeek;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType == null ? null : courseType.trim();
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}