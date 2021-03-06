package com.farata.cleardatabuilder.extjs.facet.common;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import org.eclipse.ant.launching.IAntLaunchConstants;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;

import com.farata.cleardatabuilder.extjs.Activator;
import com.farata.cleardatabuilder.extjs.util.Commons;

public class Installer {

	public static boolean install(Properties properties,
			final String projectName, boolean isNew, IProgressMonitor monitor) {
		//final AntRunner ant = new AntRunner();
		File buildFile;
		File buildPropsFile;
		try {
			buildFile = Commons.getBundleEntry(Activator.getDefault()
					.getBundle(), "install/build.xml");
			buildPropsFile = Commons.getBundleEntry(Activator.getDefault()
					.getBundle(), "install/build.properties");
			//ant.setBuildFileLocation(buildFile.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

		final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		String workspacePath = root.getLocation().toOSString();
		properties.setProperty("workspace.path", workspacePath);
		properties.setProperty("project.name", projectName.trim());

		if (isNew) {
			properties.setProperty("is.new", "true");
		} else {
			properties.setProperty("is.sample", "true");
		}

		try {
			properties.store(new FileOutputStream(buildPropsFile), "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//ant.addUserProperties(properties);

		try {

			properties.store(new FileOutputStream(buildPropsFile), "");

			ILaunchManager launchManager = DebugPlugin.getDefault()
					.getLaunchManager();
			ILaunchConfigurationType lcType = launchManager
					.getLaunchConfigurationType(IAntLaunchConstants.ID_ANT_LAUNCH_CONFIGURATION_TYPE);

			String name = launchManager
					.generateLaunchConfigurationName("Run Ant");
			ILaunchConfigurationWorkingCopy wc = lcType.newInstance(null, name);
			wc.setAttribute(ILaunchManager.ATTR_PRIVATE, true);
			wc.setAttribute("org.eclipse.ui.externaltools.ATTR_LOCATION",
					buildFile.getAbsolutePath());

			ILaunch res = wc.launch(ILaunchManager.RUN_MODE, monitor);
			while (!res.isTerminated()) {
				Thread.sleep(100);
			}
			

			IProject prj = root.getProject(projectName.trim());
			prj.open(monitor);
			prj.refreshLocal(IResource.DEPTH_INFINITE, monitor);
			prj.build(IncrementalProjectBuilder.CLEAN_BUILD, monitor);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}
}