/*
 * Copyright (C) 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.alicloud.context.acm;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.alicloud.context.AliCloudProperties;
import org.springframework.cloud.alicloud.context.edas.EdasContextAutoConfiguration;
import org.springframework.cloud.alicloud.context.edas.EdasProperties;
import org.springframework.context.annotation.Configuration;

import com.alibaba.cloud.context.acm.AliCloudAcmInitializer;

/**
 * @author xiaolongzuo
 */
@Configuration
@EnableConfigurationProperties(AcmProperties.class)
@ConditionalOnClass(name = "org.springframework.cloud.alicloud.acm.AcmAutoConfiguration")
@ImportAutoConfiguration(EdasContextAutoConfiguration.class)
public class AcmContextBootstrapConfiguration {

	@Autowired
	private AcmProperties acmProperties;

	@Autowired
	private EdasProperties edasProperties;

	@Autowired
	private AliCloudProperties aliCloudProperties;

	@PostConstruct
	public void initAcmProperties() {
		AliCloudAcmInitializer.initialize(aliCloudProperties, edasProperties,
				acmProperties);
	}

}
