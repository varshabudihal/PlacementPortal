package pojo;


public class Event
{
	int		eventId;
	String	title;
	String	description;
	String	whichDept;
	String	whichYear;
	String type;
	int		reqdAggregate;
	String	venue;
	String interest;

	public int getEventId()
	{
		return eventId;
	}

	public void setEventId(int eventId)
	{
		this.eventId = eventId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getWhichDept()
	{
		return whichDept;
	}

	public void setWhichDept(String whichDept)
	{
		this.whichDept = whichDept;
	}

	public String getWhichYear()
	{
		return whichYear;
	}

	public void setWhichYear(String whichYear)
	{
		this.whichYear = whichYear;
	}

	public int getReqdAggregate()
	{
		return reqdAggregate;
	}

	public void setReqdAggregate(int reqdAggregate)
	{
		this.reqdAggregate = reqdAggregate;
	}

	public String getVenue()
	{
		return venue;
	}

	public void setVenue(String venue)
	{
		this.venue = venue;
	}
	

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getInterest()
	{
		return interest;
	}

	public void setInterest(String interest)
	{
		this.interest = interest;
	}

	@Override
	public String toString()
	{
		return "Event [eventId=" + eventId + ", title=" + title + ", description=" + description + ", whichDept=" + whichDept + ", whichYear=" + whichYear + ", reqdAggregate=" + reqdAggregate + ", venue=" + venue + "]";
	}

	
}
