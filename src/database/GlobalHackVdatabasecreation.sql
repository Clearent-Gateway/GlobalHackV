USE [master]
GO
/****** Object:  Database [GlobalHackV]    Script Date: 9/12/2015 6:03:40 PM ******/
CREATE DATABASE [GlobalHackV]
GO
ALTER DATABASE [GlobalHackV] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [GlobalHackV].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [GlobalHackV] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [GlobalHackV] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [GlobalHackV] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [GlobalHackV] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [GlobalHackV] SET ARITHABORT OFF 
GO
ALTER DATABASE [GlobalHackV] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [GlobalHackV] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [GlobalHackV] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [GlobalHackV] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [GlobalHackV] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [GlobalHackV] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [GlobalHackV] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [GlobalHackV] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [GlobalHackV] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [GlobalHackV] SET  ENABLE_BROKER 
GO
ALTER DATABASE [GlobalHackV] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [GlobalHackV] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [GlobalHackV] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [GlobalHackV] SET ALLOW_SNAPSHOT_ISOLATION ON 
GO
ALTER DATABASE [GlobalHackV] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [GlobalHackV] SET READ_COMMITTED_SNAPSHOT ON 
GO
ALTER DATABASE [GlobalHackV] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [GlobalHackV] SET RECOVERY FULL 
GO
ALTER DATABASE [GlobalHackV] SET  MULTI_USER 
GO
ALTER DATABASE [GlobalHackV] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [GlobalHackV] SET DB_CHAINING OFF 
GO
USE [GlobalHackV]
GO
/****** Object:  Table [dbo].[citations]    Script Date: 9/12/2015 6:03:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[citations](
	[id] [bigint] NOT NULL,
	[citationNumber] [bigint] NOT NULL,
	[citationDate] [datetime] NULL,
	[firstName] [nvarchar](255) NULL,
	[lastName] [nvarchar](255) NULL,
	[dateOfBirth] [datetime] NULL,
	[address] [nvarchar](255) NULL,
	[city] [nvarchar](255) NULL,
	[state] [nvarchar](10) NULL,
	[driversLicense] [nvarchar](255) NULL,
	[courtDate] [datetime] NULL,
	[municipality] [nvarchar](255) NULL,
	[courtAddress] [nvarchar](255) NULL,
 CONSTRAINT [PKCL_citationnumber] PRIMARY KEY CLUSTERED 
(
	[citationNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)

GO
/****** Object:  Table [dbo].[municipalities]    Script Date: 9/12/2015 6:03:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[municipalities](
	[Municipality] [nvarchar](255) NOT NULL,
	[Municipal Court] [nvarchar](255) NULL,
	[Municipal Website] [nvarchar](255) NULL,
	[Municipal Court Website] [nvarchar](255) NULL,
	[Court Docket Time/Place Listed?] [nvarchar](255) NULL,
	[Court Clerk Phone Number Listed on Muni Site?] [nvarchar](255) NULL,
	[Info obtained from:] [nvarchar](255) NULL,
	[Online Payment System Provider] [nvarchar](255) NULL,
	[Mention of Dress Code?] [nvarchar](255) NULL,
	[Fine Schedule Listed?] [nvarchar](255) NULL,
	[Municipal Codes Listed?] [nvarchar](255) NULL,
	[Order Seen Stated?] [nvarchar](255) NULL,
	[Directions to Court Listed?] [nvarchar](255) NULL,
 CONSTRAINT [PK_municipalities] PRIMARY KEY CLUSTERED 
(
	[Municipality] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)

GO
/****** Object:  Table [dbo].[sms_citations]    Script Date: 9/12/2015 6:03:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sms_citations](
	[id] [bigint] NOT NULL,
	[phoneNumber] [varchar](15) NOT NULL,
	[citationNumber] [bigint] NOT NULL,
	[citationDate] [datetime] NULL,
	[firstName] [nvarchar](255) NULL,
	[lastName] [nvarchar](255) NULL,
	[dateOfbirth] [datetime] NULL,
	[Address] [nvarchar](255) NULL,
	[City] [nvarchar](255) NULL,
	[State] [nvarchar](255) NULL,
	[driversLicense] [nvarchar](255) NULL,
	[courtDate] [datetime] NULL,
	[municipality] [nvarchar](255) NULL,
	[courtAddress] [nvarchar](255) NULL,
	[requestDatetime] [datetime] NOT NULL
)

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[users]    Script Date: 9/12/2015 6:03:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[LastName] [nvarchar](255) NULL,
	[FirstName] [nvarchar](255) NULL,
	[Birthday] [datetime] NULL,
	[Address] [nvarchar](255) NULL,
	[City] [nvarchar](255) NULL,
	[State] [nvarchar](10) NULL,
	[LicenseNumber] [nvarchar](255) NULL,
 CONSTRAINT [PKCL_CitizenId] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)

GO
/****** Object:  Table [dbo].[violationRef]    Script Date: 9/12/2015 6:03:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[violationRef](
	[Violation] [nvarchar](255) NULL,
	[FINE] [money] NULL,
	[COURT COSTS] [nvarchar](255) NULL,
	[TOTAL] [nvarchar](255) NULL
)

GO
/****** Object:  Table [dbo].[violations]    Script Date: 9/12/2015 6:03:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[violations](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[citationNumber] [bigint] NOT NULL,
	[violationNumber] [nvarchar](255) NOT NULL,
	[violationDescription] [nvarchar](255) NULL,
	[warrantStatus] [bit] NULL,
	[warrantNumber] [nvarchar](255) NULL,
	[status] [nvarchar](255) NULL,
	[statusDate] [datetime] NULL,
	[fineAmount] [money] NULL,
	[courtCosts] [money] NULL,
 CONSTRAINT [PKCL_violations] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)

GO
ALTER TABLE [dbo].[citations]  WITH CHECK ADD  CONSTRAINT [FK_citations_municipality] FOREIGN KEY([municipality])
REFERENCES [dbo].[municipalities] ([Municipality])
GO
ALTER TABLE [dbo].[citations] CHECK CONSTRAINT [FK_citations_municipality]
GO
ALTER TABLE [dbo].[violations]  WITH CHECK ADD  CONSTRAINT [FK_violations_citationnum] FOREIGN KEY([citationNumber])
REFERENCES [dbo].[citations] ([citationNumber])
GO
ALTER TABLE [dbo].[violations] CHECK CONSTRAINT [FK_violations_citationnum]
GO
USE [master]
GO
ALTER DATABASE [GlobalHackV] SET  READ_WRITE 
GO
