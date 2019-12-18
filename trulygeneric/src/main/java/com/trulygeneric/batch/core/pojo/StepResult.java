package com.trulygeneric.batch.core.pojo;


public class StepResult {
	
	private String stepName;
	private String status;
	private String exitStatus;
	private int readCount = 0;
	private int writeCount = 0;
	private int commitCount = 0;
	private int rollbackCount = 0;
	private int readSkipCount = 0;
	private int processSkipCount = 0;
	private int writeSkipCount = 0;
	private boolean terminateOnly;
	private int filterCount;
	
	public StepResult(String stepName, String status, String exitStatus) {
		this.stepName = stepName;
		this.status = status;
		this.exitStatus = exitStatus;
	}
	
	public StepResult setCount(int readCount, int writeCount, int commitCount) {
		this.readCount = readCount;
		this.writeCount = writeCount;
		this.commitCount = commitCount;
		return this;
	}

	public StepResult setSkipCount(int readSkipCount, int processSkipCount, int writeSkipCount) {
		this.readSkipCount = readSkipCount;
		this.processSkipCount = processSkipCount;
		this.writeSkipCount = writeSkipCount;
		return this;
	}

	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getWriteCount() {
		return writeCount;
	}
	public void setWriteCount(int writeCount) {
		this.writeCount = writeCount;
	}
	public int getCommitCount() {
		return commitCount;
	}
	public void setCommitCount(int commitCount) {
		this.commitCount = commitCount;
	}
	public int getRollbackCount() {
		return rollbackCount;
	}
	public void setRollbackCount(int rollbackCount) {
		this.rollbackCount = rollbackCount;
	}
	public int getReadSkipCount() {
		return readSkipCount;
	}
	public void setReadSkipCount(int readSkipCount) {
		this.readSkipCount = readSkipCount;
	}
	public int getProcessSkipCount() {
		return processSkipCount;
	}
	public void setProcessSkipCount(int processSkipCount) {
		this.processSkipCount = processSkipCount;
	}
	public int getWriteSkipCount() {
		return writeSkipCount;
	}
	public void setWriteSkipCount(int writeSkipCount) {
		this.writeSkipCount = writeSkipCount;
	}
	public boolean isTerminateOnly() {
		return terminateOnly;
	}
	public void setTerminateOnly(boolean terminateOnly) {
		this.terminateOnly = terminateOnly;
	}
	public int getFilterCount() {
		return filterCount;
	}
	public void setFilterCount(int filterCount) {
		this.filterCount = filterCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExitStatus() {
		return exitStatus;
	}
	public void setExitStatus(String exitStatus) {
		this.exitStatus = exitStatus;
	}
	
	
	
	@Override
	public String toString() {
		return "StepResult [stepName=" + stepName + ", status=" + status
				+ ", readCount=" + readCount + ", writeCount=" + writeCount
				+ ", commitCount=" + commitCount + ", rollbackCount="
				+ rollbackCount + ", readSkipCount=" + readSkipCount
				+ ", processSkipCount=" + processSkipCount
				+ ", writeSkipCount=" + writeSkipCount + ", exitStatus="
				+ exitStatus + ", terminateOnly=" + terminateOnly
				+ ", filterCount=" + filterCount + "]";
	}
	
}
